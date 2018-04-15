package com.bytx.admin.service;

import com.bytx.admin.dao.CompanyInfoDao;
import com.bytx.admin.entity.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyInfoPersistenceService
{
    @Autowired
    private CompanyInfoDao companyInfoDao;

    public Integer updateCompanyInfo(CompanyInfo companyInfo)
    {
        return companyInfoDao.updateCompanyInfo(companyInfo);
    }
}
