package com.bytx.admin.controller;

import com.bytx.admin.entity.Rotation;
import com.bytx.admin.service.MenuPersistenceService;
import com.bytx.admin.util.SFTPUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/rotation")
public class RotationController extends BaseController
{
    @Autowired
    private MenuPersistenceService menuPersistenceService;

    @RequestMapping("/addRotation")
    @ResponseBody
    public Integer addImage(MultipartFile imageFile, HttpServletRequest request, Rotation rotation)
    {
        String basePath = request.getServletContext().getRealPath("/");

        String imgFileName = imageFile.getOriginalFilename();

        String rootPath = basePath + storageImagePath + "/rotation";
        String imgPath = rootPath + "/img/" + imgFileName;

        String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/rotation/img";

        String remoteImgUrl = accessImageUrl + storageImagePath + "/rotation/img/" + imgFileName;

        File tmpImgFile = new File(imgPath);

        try
        {
            FileUtils.forceMkdir(tmpImgFile.getParentFile());

            imageFile.transferTo(tmpImgFile);

            SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);

            rotation.setImageUrl(remoteImgUrl);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return menuPersistenceService.addImage(rotation);
    }

    @RequestMapping("/updateRotation")
    @ResponseBody
    public Integer updateImage(MultipartFile updateImageFile, HttpServletRequest request, Rotation rotation)
    {
        String basePath = request.getServletContext().getRealPath("/");

        if (updateImageFile != null)
        {
            String imgFileName = updateImageFile.getOriginalFilename();

            String rootPath = basePath + storageImagePath + "/rotation";
            String imgPath = rootPath + "/img/" + imgFileName;

            String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/rotation/img";

            String remoteImgUrl = accessImageUrl + storageImagePath + "/rotation/img/" + imgFileName;

            File tmpImgFile = new File(imgPath);

            try
            {
                FileUtils.forceMkdir(tmpImgFile.getParentFile());

                updateImageFile.transferTo(tmpImgFile);

                SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);

                rotation.setImageUrl(remoteImgUrl);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return menuPersistenceService.updateImage(rotation);
    }
}
