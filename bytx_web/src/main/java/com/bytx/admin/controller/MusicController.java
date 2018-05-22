package com.bytx.admin.controller;

import com.bytx.admin.entity.Music;
import com.bytx.admin.service.MusicPersistenceService;
import com.bytx.admin.service.MusicQueryService;
import com.bytx.admin.util.MusicUtil;
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
@RequestMapping("/music")
public class MusicController extends BaseController
{
    @Autowired
    private MusicQueryService musicQueryService;

    @Autowired
    private MusicPersistenceService musicPersistenceService;

    @RequestMapping("/addMusic")
    @ResponseBody
    public Integer addMusic(HttpServletRequest request, Music music)
    {
        String basePath = request.getServletContext().getRealPath("/");

        MultipartFile imgFile = ((MultipartHttpServletRequest) request).getFile("imageFile");
        MultipartFile musicFile = ((MultipartHttpServletRequest) request).getFile("musicFile");

        String imgFileName = imgFile.getOriginalFilename();
        String musicFileName = musicFile.getOriginalFilename();

        String rootPath = basePath + storageImagePath + "/music";
        String imgPath = rootPath + "/img/" + imgFileName;
        String musicPath = rootPath + "/musicFile/" + musicFileName;

        String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/music/img";
        String remoteMusicPath = "/data/wwwroot/default" + storageImagePath + "/music/musicFile";

        String remoteImgUrl = accessImageUrl + storageImagePath + "/music/img/" + imgFileName.substring(0, imgFileName.lastIndexOf("."));
        String remoteMusicUrl = accessImageUrl + storageImagePath + "/music/musicFile/" + musicFileName;

        File tmpImgFile = new File(imgPath);
        File tmpMusicFile = new File(musicPath);

        try
        {
            FileUtils.forceMkdir(tmpImgFile.getParentFile());
            FileUtils.forceMkdir(tmpMusicFile.getParentFile());

            imgFile.transferTo(tmpImgFile);
            musicFile.transferTo(tmpMusicFile);

            SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);
            SFTPUtil.uploadFile(BaseController.channelSftp, musicPath, remoteMusicPath);

            music.setMusicImagesPath(remoteImgUrl);
            music.setMusicPath(remoteMusicUrl);
            music.setMusicTime(MusicUtil.getMusicTime(tmpMusicFile));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return musicPersistenceService.addMusic(music);
    }

    @RequestMapping("/updateMusic")
    @ResponseBody
    public Integer updateMusic(HttpServletRequest request, Music music)
    {
        String basePath = request.getServletContext().getRealPath("/");

        MultipartFile imgFile = ((MultipartHttpServletRequest) request).getFile("updateImageFile");
        MultipartFile musicFile = ((MultipartHttpServletRequest) request).getFile("updateMusicFile");

        if (imgFile != null && musicFile != null)
        {
            String imgFileName = imgFile.getOriginalFilename();
            String musicFileName = musicFile.getOriginalFilename();

            String rootPath = basePath + storageImagePath + "/music";
            String imgPath = rootPath + "/img/" + imgFileName;
            String musicPath = rootPath + "/musicFile/" + musicFileName;

            String remoteImgPath = "/data/wwwroot/default" + storageImagePath + "/music/img";
            String remoteMusicPath = "/data/wwwroot/default" + storageImagePath + "/music/musicFile";

            String remoteMusicUrl = accessImageUrl + storageImagePath + "/music/musicFile/" + musicFileName;

            File tmpImgFile = new File(imgPath);
            File tmpMusicFile = new File(musicPath);

            try
            {
                FileUtils.forceMkdir(tmpImgFile.getParentFile());
                FileUtils.forceMkdir(tmpMusicFile.getParentFile());

                imgFile.transferTo(tmpImgFile);
                musicFile.transferTo(tmpMusicFile);

                SFTPUtil.uploadFile(BaseController.channelSftp, imgPath, remoteImgPath);
                SFTPUtil.uploadFile(BaseController.channelSftp, musicPath, remoteMusicPath);

                music.setMusicPath(remoteMusicUrl);
                music.setMusicTime(MusicUtil.getMusicTime(tmpMusicFile));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return musicPersistenceService.updateMusicInfoById(music);
    }
}
