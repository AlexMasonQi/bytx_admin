package com.bytx.admin.service;

import com.bytx.admin.dao.MenuDao;
import com.bytx.admin.dao.RotationDao;
import com.bytx.admin.entity.Menu;
import com.bytx.admin.entity.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuQueryService
{
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RotationDao rotationDao;

    public List<Menu> selectAllFirstMenu()
    {
        return menuDao.selectAllFirstMenu();
    }

    public List<Menu> selectSecondMenu()
    {
        return menuDao.selectAllSecondMenu();
    }

    public List<Menu> selectThirdMenu()
    {
        return menuDao.selectAllThirdMenu();
    }

    public Menu selectMenuById(Integer id)
    {
        return menuDao.selectMenuById(id);
    }

    public ArrayList<Menu> selectSonMenuByParentId(Integer id, Integer level)
    {
        return menuDao.selectSonMenuByParentId(id, level);
    }

    public Integer selectImagesCount()
    {
        return rotationDao.selectImagesCount();
    }

    public List<Rotation> selectImagesByCount(Integer count)
    {
        return rotationDao.selectImageRotationByCount(count);
    }
}
