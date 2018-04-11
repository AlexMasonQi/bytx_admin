package com.bytx.admin.controller;

import com.bytx.admin.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author Alex
 * @description 基类描述
 * @date 2018.04.02 22:16
 */
public class BaseController
{
    /**
     * @param
     * @return 当前Session
     * @description 获取当前Session信息
     * @author Alex
     * @date 2018.04.02 22:18
     */
    public Session getCurrentSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * @param
     * @return 当前登录用户
     * @description 获取当前登录用户信息
     * @author Alex
     * @date 2018.04.02 22:20
     */
    public User getLoginUser()
    {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");

        return user;
    }
}
