package com.bytx.admin.dao;

import com.bytx.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MenuDao
{
    List<Menu> selectAllFirstMenu();

    List<Menu> selectAllSecondMenu();

    List<Menu> selectAllThirdMenu();

    Menu selectMenuById(@Param("id") Integer id);

    ArrayList<Menu> selectSonMenuByParentId(@Param("id") Integer id, @Param("level") Integer level);
}
