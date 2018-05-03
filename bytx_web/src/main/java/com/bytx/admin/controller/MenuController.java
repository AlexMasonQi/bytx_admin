package com.bytx.admin.controller;

import com.bytx.admin.entity.*;
import com.bytx.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController
{
    @Autowired
    private BasicInfoQueryService basicInfoQueryService;

    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;

    @Autowired
    private MediaQueryService mediaQueryService;

    @Autowired
    private MusicQueryService musicQueryService;

    @Autowired
    private NewsCenterQueryService newsCenterQueryService;

    @Autowired
    private MenuQueryService menuQueryService;

    @Autowired
    private VideoQueryService videoQueryService;

    @RequestMapping("/companyBaseInfo")
    public String showCompanyBaseInfo(Map model)
    {
        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();

        model.put("basicInfo", basicInfo);

        return "admin/companybaseinfo";
    }

    @RequestMapping("/companyInfo")
    public String showCompanyInfo(Map model)
    {
        List<CompanyInfo> companyInfoList = companyInfoQueryService.selectAllCompanyInfo();
        model.put("companyInfoList", companyInfoList);

        return "admin/companyinfo";
    }

    @RequestMapping("/companyMedia")
    public String showCompanyMedia(Map model)
    {
        List<Media> mediaList = mediaQueryService.selectAllMedia();
        model.put("mediaList", mediaList);

        return "admin/companymedia";
    }

    @RequestMapping("/companyMusic")
    public String showCompanyMusic(Map model)
    {
        List<Music> musicList = musicQueryService.selectAllSongs();
        model.put("musicList", musicList);

        return "admin/companymusic";
    }

    @RequestMapping("/companyNews")
    public String showCompanyNews(Map model)
    {
        List<NewsCenter> newsCenterList = newsCenterQueryService.selectAllNews();
        model.put("newsList", newsCenterList);

        return "admin/companynews";
    }

    @RequestMapping("/companyRotation")
    public String showCompanyRotation(Map model)
    {
        List<Rotation> rotationList = menuQueryService.selectAllImages();
        model.put("rotationList", rotationList);

        return "admin/companyrotation";
    }

    @RequestMapping("/companyVideo")
    public String showCompanyVideo(Map model)
    {
        List<Video> videoList = videoQueryService.selectAllVideos();
        model.put("videoList", videoList);

        return "admin/companyvideo";
    }

    @RequestMapping("/testFile")
    public String showTestUploadPage()
    {
        return "testUpload";
    }
}
