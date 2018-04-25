package com.bytx.admin.controller;

import com.bytx.admin.util.SFTPUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FilesController extends BaseController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static ChannelSftp channelSftp = SFTPUtil.getChannel("47.104.142.179", "root", "BJbytx1234567", 22);

    /**
     * @description CKEDITOR上传图片
     * @author Alex
     * @date 2018.04.23 17:57
     */
    @RequestMapping(value = "/ckeditor_image", method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum, HttpServletRequest request)
    {
        ResponseEntity<String> responseEntity = null;
        if (!file.isEmpty())
        {
            HttpHeaders headers = new HttpHeaders();

            try
            {
                headers.setContentType(MediaType.TEXT_HTML);
                headers.setCacheControl("no-cache");

                //解决跨域问题
                headers.set("X-Frame-Options", "SAMEORIGIN");

                String fileName = file.getOriginalFilename();
                String tempPath = request.getServletContext().getRealPath("/") + storageImagePath + "/ckedtor/images/" + fileName;
                String desPath = "/data/wwwroot/default" + storageImagePath + "/ckeditor/images";

                File imageFile = new File(tempPath);
                FileUtils.forceMkdir(imageFile.getParentFile());

                file.transferTo(imageFile);

                SFTPUtil.uploadFile(channelSftp, tempPath, desPath);
                SFTPUtil.closeConnection(channelSftp);

                // 组装返回url，以便于ckeditor定位图片
                String fileUrl = accessImageUrl + storageImagePath + "/ckeditor/images/" + imageFile.getName();
                // 将上传的图片的url返回给ckeditor
                System.out.println("callback = " + CKEditorFuncNum);
                String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ", '" + fileUrl + "');</script>";

                responseEntity = new ResponseEntity<>(script, headers, HttpStatus.OK);
            }
            catch (IOException e)
            {
                HttpHeaders excHeader = new HttpHeaders();
                logger.error("Upload error====>", e);
                responseEntity = new ResponseEntity<>(excHeader, HttpStatus.BAD_GATEWAY);
            }
            catch (JSchException e)
            {
                logger.error("Upload to server failed!", e);
                HttpHeaders excHeader = new HttpHeaders();
                responseEntity = new ResponseEntity<>(excHeader, HttpStatus.BAD_GATEWAY);
            }
        }
        else
        {
            HttpHeaders noFileHeader = new HttpHeaders();
            String resp = "";
            responseEntity = new ResponseEntity<>(resp, noFileHeader, HttpStatus.BAD_REQUEST);
            logger.info("File not found!");
        }

        return responseEntity;
    }
}
