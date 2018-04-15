package com.bytx.admin.service;

import com.bytx.admin.dao.BasicInfoDao;
import com.bytx.admin.entity.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyBaseInfoPersistenceService
{
    @Autowired
    private BasicInfoDao basicInfoDao;

    public Integer updateCompanyBaseInfo(BasicInfo basicInfo)
    {
        return basicInfoDao.updateCompanyBaseInfo(basicInfo);
    }
}
