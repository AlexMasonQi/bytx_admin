package com.bytx.admin.dao;

import com.bytx.admin.entity.BasicInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoDao
{
    BasicInfo selectBasicInfoByStatus();
}
