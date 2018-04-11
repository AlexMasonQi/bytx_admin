package com.bytx.admin.service;

import com.bytx.admin.dao.CompanyInfoDao;
import com.bytx.admin.entity.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfoQueryService
{
    @Autowired
    private CompanyInfoDao companyInfoDao;

    public CompanyInfo selectCompanyInfoById(Integer id)
    {
        return companyInfoDao.selectCompanyInfoById(id);
    }
}
