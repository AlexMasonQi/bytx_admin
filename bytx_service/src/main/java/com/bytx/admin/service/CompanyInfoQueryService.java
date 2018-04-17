package com.bytx.admin.service;

import com.bytx.admin.dao.CompanyInfoDao;
import com.bytx.admin.entity.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInfoQueryService
{
    @Autowired
    private CompanyInfoDao companyInfoDao;

    public CompanyInfo selectCompanyInfoById(Integer id)
    {
        return companyInfoDao.selectCompanyInfoById(id);
    }

    public List<CompanyInfo> selectAllCompanyInfo()
    {
        return companyInfoDao.selectAllCompanyInfo();
    }
}
