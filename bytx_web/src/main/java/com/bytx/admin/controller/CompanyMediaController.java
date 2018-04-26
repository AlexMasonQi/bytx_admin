package com.bytx.admin.controller;

import com.bytx.admin.entity.Media;
import com.bytx.admin.service.CompanyMediaPersistenceService;
import com.bytx.admin.service.MediaQueryService;
import com.bytx.admin.util.SFTPUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/media")
public class CompanyMediaController extends BaseController
{
    @Autowired
    private MediaQueryService mediaQueryService;

    @Autowired
    private CompanyMediaPersistenceService companyMediaPersistenceService;

    @RequestMapping("/showMedia")
    public String showMediaInfo(Map model)
    {
        List<Media> mediaList = mediaQueryService.selectAllMedia();
        model.put("mediaList", mediaList);

        return "admin/companymedia";
    }

    @RequestMapping("/updateMediaInfo")
    @ResponseBody
    public Integer updateMediaInfo(@RequestParam("imageFile") MultipartFile file, HttpServletRequest request, Media media)
    {
        List<Media> mediaList = mediaQueryService.selectAllMedia();
        for (var media1 : mediaList)
        {
            if (media.getId().equals(media1.getId()))
            {
                media.setMediaId(media1.getMediaId());
                break;
            }
        }

        String originalFileName = file.getOriginalFilename();
        String basePath = request.getServletContext().getRealPath("/");
        String rootPath = basePath + storageImagePath + "/media";
        String tempPath = rootPath + "/" + media.getMediaId() + "/" + originalFileName;

        File tempFile = new File(tempPath);
        String remotePath = "/data/wwwroot/default" + storageImagePath + "/media/" + media.getMediaId();
        String remoteUrl = accessImageUrl + storageImagePath + "/media/" + media.getMediaId() + "/" + originalFileName;

        try
        {
            FileUtils.forceMkdir(tempFile.getParentFile());
            file.transferTo(tempFile);

            SFTPUtil.uploadFile(BaseController.channelSftp, tempPath, remotePath);

            media.setImages(remoteUrl);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return companyMediaPersistenceService.updateMedia(media);
    }
}
