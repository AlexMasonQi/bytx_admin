package com.bytx.admin.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/upload")
public class FilesController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value(value = "${ckeditor.storage.image.path}")
    private String ckeditorStorageImagePath;

    @Value(value = "${ckeditor.access.image.url}")
    private String ckeditorAccessImageUrl;

    /**
     * @description CKEDITOR上传图片
     * @author Alex
     * @date 2018.04.23 17:57
     */
    @RequestMapping("/ckeditor_image")
    @ResponseBody
    public String uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        String result = null;

        if (!file.isEmpty())
        {
            PrintWriter writer = null;

            try
            {
                response.setContentType("text/html;charset=utf-8");
                response.setHeader("Cache-Control", "no-cache");

                //解决跨域问题
                //Refused to display 'http://localhost:8080/upload/mgmt/img?CKEditor=practice_content&CKEditorFuncNum=1&langCode=zh-cn' in a frame because it set 'X-Frame-Options' to 'DENY'.
                response.setHeader("X-Frame-Options", "SAMEORIGIN");

                writer = response.getWriter();

                String fileName = file.getOriginalFilename();
                String path = SystemUtils.USER_DIR + File.separator + "upload" + File.separator + ckeditorStorageImagePath + File.separator + fileName;

                File imageFile = new File(path);
                FileUtils.forceMkdir(imageFile.getParentFile());

                file.transferTo(imageFile);

                // 组装返回url，以便于ckeditor定位图片
                String fileUrl = ckeditorAccessImageUrl + File.separator + imageFile.getName();

                // 将上传的图片的url返回给ckeditor
                String callback = request.getParameter("CKEditorFuncNum");
                String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + fileUrl + "');</script>";

                writer.println(script);
                writer.flush();
                writer.close();

                result = "SUCCESS";
            }
            catch (IOException e)
            {
                logger.error("Upload error====>", e);
                result = "FAILED";
            }
        }
        else
        {
            logger.info("File not found!");
            result = "FAILED";
        }

        return result;
    }
}
