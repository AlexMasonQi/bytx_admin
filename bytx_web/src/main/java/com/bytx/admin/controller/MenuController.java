package com.bytx.admin.controller;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController
{
    @RequestMapping("/companyBaseInfo")
    public String showCompanyBaseInfo(Map model)
    {
        return "admin/companybaseinfo";
    }

    @RequestMapping("/companyInfo")
    public String showCompanyInfo(Map model)
    {
        return "admin/companyinfo";
    }

    @RequestMapping("/companyMedia")
    public String showCompanyMedia(Map model)
    {
        return "admin/companymedia";
    }

    @RequestMapping("/companyMusic")
    public String showCompanyMusic(Map model)
    {
        return "admin/companymusic";
    }

    @RequestMapping("/companyNews")
    public String showCompanyNews(Map model)
    {
        return "admin/companynews";
    }

    @RequestMapping("/companyRotation")
    public String showCompanyRotation(Map model)
    {
        return "admin/companyrotation";
    }

    @RequestMapping("/companyVideo")
    public String showCompanyVideo(Map model)
    {
        return "admin/companyvideo";
    }
}
