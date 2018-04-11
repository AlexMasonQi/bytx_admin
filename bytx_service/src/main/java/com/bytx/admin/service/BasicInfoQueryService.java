package com.bytx.admin.service;

import com.bytx.admin.dao.BasicInfoDao;
import com.bytx.admin.entity.BasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoQueryService
{
    @Autowired
    private BasicInfoDao basicInfoDao;

    public BasicInfo selectBasicInfoByStatus()
    {
        return basicInfoDao.selectBasicInfoByStatus();
    }
}
