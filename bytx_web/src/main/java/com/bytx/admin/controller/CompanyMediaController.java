package com.bytx.admin.controller;

import com.bytx.admin.entity.Media;
import com.bytx.admin.service.MediaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/media")
public class CompanyMediaController extends BaseController
{
    @Autowired
    private MediaQueryService mediaQueryService;

    @RequestMapping("/showMedia")
    public String showMediaInfo(Map model)
    {
        List<Media> mediaList = mediaQueryService.selectAllMedia();
        model.put("mediaList", mediaList);

        return "admin/companymedia";
    }
}
