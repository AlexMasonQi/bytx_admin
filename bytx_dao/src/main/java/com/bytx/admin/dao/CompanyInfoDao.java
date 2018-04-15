package com.bytx.admin.dao;

import com.bytx.admin.entity.CompanyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoDao
{
    CompanyInfo selectCompanyInfoById(Integer id);

    int updateCompanyInfo(CompanyInfo companyInfo);
}
