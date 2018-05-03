package com.bytx.admin.controller;

import com.bytx.admin.entity.Video;
import com.bytx.admin.service.VideoPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/video")
public class VideoController extends BaseController
{
    @Autowired
    private VideoPersistenceService videoPersistenceService;

    @RequestMapping("/addVideo")
    @ResponseBody
    public Integer addVideo(Video video)
    {
        return videoPersistenceService.addVideo(video);
    }

    @RequestMapping("/updateVideo")
    @ResponseBody
    public Integer updateVideo(Video video)
    {
        return videoPersistenceService.updateVideo(video);
    }
}
