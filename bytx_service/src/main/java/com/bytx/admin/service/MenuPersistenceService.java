package com.bytx.admin.service;

import com.bytx.admin.dao.MenuDao;
import com.bytx.admin.dao.RotationDao;
import com.bytx.admin.entity.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuPersistenceService
{
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RotationDao rotationDao;

    public Integer addImage(Rotation rotation)
    {
        return rotationDao.addImage(rotation);
    }

    public Integer updateImage(Rotation rotation)
    {
        return rotationDao.updateImage(rotation);
    }
}
