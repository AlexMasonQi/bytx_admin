package com.bytx.admin.controller;

import com.bytx.admin.entity.BasicInfo;
import com.bytx.admin.service.CompanyBaseInfoPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/info")
public class CompanyBaseInfoController extends BaseController
{
    @Autowired
    private CompanyBaseInfoPersistenceService companyBaseInfoPersistenceService;

    @RequestMapping("/updateBasicInfo")
    @ResponseBody
    public Integer updateCompanyBaseInfo(BasicInfo basicInfo)
    {
        basicInfo.setId(1);
        return companyBaseInfoPersistenceService.updateCompanyBaseInfo(basicInfo);
    }
}
