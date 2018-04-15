package com.bytx.admin.controller;

import com.bytx.admin.entity.CompanyInfo;
import com.bytx.admin.service.CompanyInfoPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("companyInfo")
public class CompanyInfoController extends BaseController
{
    @Autowired
    private CompanyInfoPersistenceService companyInfoPersistenceService;

    @RequestMapping("/updateInfo")
    @ResponseBody
    public Integer updateCompanyInfo(CompanyInfo companyInfo)
    {
        return companyInfoPersistenceService.updateCompanyInfo(companyInfo);
    }
}
