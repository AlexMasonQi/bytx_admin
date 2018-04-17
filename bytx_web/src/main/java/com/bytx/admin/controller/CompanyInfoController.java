package com.bytx.admin.controller;

import com.bytx.admin.entity.CompanyInfo;
import com.bytx.admin.service.CompanyInfoPersistenceService;
import com.bytx.admin.service.CompanyInfoQueryService;
import com.bytx.admin.service.MenuQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("companyInfo")
public class CompanyInfoController extends BaseController
{
    @Autowired
    private CompanyInfoPersistenceService companyInfoPersistenceService;

    @Autowired
    private CompanyInfoQueryService companyInfoQueryService;

    @Autowired
    private MenuQueryService menuQueryService;

    @RequestMapping("/showCompanyInfo")
    public String showCompanyInfo(Map model)
    {
        List<CompanyInfo> companyInfoList = companyInfoQueryService.selectAllCompanyInfo();
        model.put("companyInfoList", companyInfoList);

        return "admin/companyinfo";
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public Integer updateCompanyInfo(CompanyInfo companyInfo)
    {
        return companyInfoPersistenceService.updateCompanyInfo(companyInfo);
    }
}
