package com.bytx.admin.controller;

import com.bytx.admin.entity.NewsCenter;
import com.bytx.admin.service.NewsCenterPersistenceService;
import com.bytx.admin.util.SFTPUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController
{
    @Autowired
    private NewsCenterPersistenceService newsCenterPersistenceService;

    @RequestMapping("/addNews")
    @ResponseBody
    public Integer addNews(MultipartFile imageFile, HttpServletRequest request, NewsCenter newsCenter)
    {
        String basePath = request.getServletContext().getRealPath("/");

        String imgFileName = imageFile.getOriginalFilename();

        String rootPath = basePath + storageImagePath + "/news";
        String imgPath = rootPath + "/img/" + imgFileName;

        String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/news/img";

        String remoteImgUrl = accessImageUrl + storageImagePath + "/news/img/" + imgFileName;

        File tmpImgFile = new File(imgPath);

        try
        {
            FileUtils.forceMkdir(tmpImgFile.getParentFile());

            imageFile.transferTo(tmpImgFile);

            SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);

            newsCenter.setImageSrc(remoteImgUrl);
            newsCenter.setParentId(2);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return newsCenterPersistenceService.addNews(newsCenter);
    }

    @RequestMapping("/updateNews")
    @ResponseBody
    public Integer updateNews(HttpServletRequest request, NewsCenter newsCenter)
    {
        MultipartFile updateImageFile = ((MultipartHttpServletRequest) request).getFile("updateImageFile");

        String basePath = request.getServletContext().getRealPath("/");
        newsCenter.setParentId(2);

        if (updateImageFile != null)
        {
            String imgFileName = updateImageFile.getOriginalFilename();

            String rootPath = basePath + storageImagePath + "/news";
            String imgPath = rootPath + "/img/" + imgFileName;

            String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/news/img";

            String remoteImgUrl = accessImageUrl + storageImagePath + "/news/img/" + imgFileName;

            File tmpImgFile = new File(imgPath);

            try
            {
                FileUtils.forceMkdir(tmpImgFile.getParentFile());

                updateImageFile.transferTo(tmpImgFile);

                SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);

                newsCenter.setImageSrc(remoteImgUrl);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return newsCenterPersistenceService.updateNews(newsCenter);
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public Integer updateNewsStatus(NewsCenter newsCenter)
    {
        return newsCenterPersistenceService.updateNews(newsCenter);
    }
}
