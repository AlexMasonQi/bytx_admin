package com.bytx.admin.service;

import com.bytx.admin.dao.UserDao;
import com.bytx.admin.entity.Permission;
import com.bytx.admin.entity.Role;
import com.bytx.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService
{
    @Autowired
    private UserDao userDao;

    /**
     * @param
     * @return List<User>集合
     * @description 获取所有用户信息
     * @author Alex
     * @date 2018.03.28 10:56
     */
    @Cacheable(value = "userList")
    public List<User> getUserList()
    {
        return userDao.getUserList();
    }

    /**
     * @param userName 登录的用户名
     * @return UUser对象
     * @description 根据用户名查找对应用户信息
     * @author Alex
     * @date 2018.03.28 10:57
     */
    public User getUserByName(String userName)
    {
        return userDao.getUserByUserName(userName);
    }

    /**
     * @param userId 用户ID
     * @return URole对象
     * @description 根据用户ID查找对应角色信息
     * @author Alex
     * @date 2018.03.28 10:58
     */
    public Role getRoleByUserId(Integer userId)
    {
        return userDao.getRoleById(userId);
    }

    /**
     * @param roleId 角色ID
     * @return List<Permission>集合
     * @description 根据角色ID查找对应的权限
     * @author Alex
     * @date 2018.03.28 14:00
     */
    public List<Permission> getPermissionsById(Integer roleId)
    {
        return userDao.getPermissionsByRoleId(roleId);
    }
}
