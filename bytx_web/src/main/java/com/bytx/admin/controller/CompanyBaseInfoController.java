package com.bytx.admin.controller;

import com.bytx.admin.entity.BasicInfo;
import com.bytx.admin.service.BasicInfoQueryService;
import com.bytx.admin.service.CompanyBaseInfoPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Integer updateCompanyBaseInfo(BasicInfo basicInfo)
    {
        basicInfo.setId(1);
        return companyBaseInfoPersistenceService.updateCompanyBaseInfo(basicInfo);
    }
}
