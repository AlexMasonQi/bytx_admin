package com.bytx.admin.controller;

import com.bytx.admin.entity.BasicInfo;
import com.bytx.admin.service.BasicInfoQueryService;
import com.bytx.admin.service.CompanyBaseInfoPersistenceService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/info")
public class CompanyBaseInfoController extends BaseController
{
    @Autowired
    private CompanyBaseInfoPersistenceService companyBaseInfoPersistenceService;

    @Autowired
    private BasicInfoQueryService basicInfoQueryService;

    @RequestMapping("/showBaseInfo")
    public String showBaseInfo(Map model)
    {
        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();

        model.put("basicInfo", basicInfo);

        return "admin/companybaseinfo";
    }

    @RequestMapping("/updateBasicInfo")
    @ResponseBody
    public Integer updateCompanyBaseInfo(BasicInfo basicInfo, HttpServletRequest request)
    {
        basicInfo.setId(1);

        MultipartFile logoFile = ((MultipartHttpServletRequest) request).getFile("logo");
        MultipartFile codeFile = ((MultipartHttpServletRequest) request).getFile("code");

        String logoName = logoFile.getOriginalFilename();
        String codeName = codeFile.getOriginalFilename();

        String rootPath = SystemUtils.USER_DIR + File.separator + "upload" + File.separator + "basic_info";
        String logoPath = rootPath + File.separator + "logo" + File.separator + logoName;
        String codePath = rootPath + File.separator + "qrcode" + File.separator + codeName;

        File lFile = new File(logoPath);
        File qFile = new File(codePath);

        try
        {
            FileUtils.forceMkdir(lFile.getParentFile());
            FileUtils.forceMkdir(qFile.getParentFile());

            logoFile.transferTo(lFile);
            codeFile.transferTo(qFile);

            basicInfo.setBasicLogo(logoPath);
            basicInfo.setBasicQrcode(codePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Integer tag = companyBaseInfoPersistenceService.updateCompanyBaseInfo(basicInfo);
        System.out.println(tag);
        return tag;
    }
}
