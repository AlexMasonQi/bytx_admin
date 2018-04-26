package com.bytx.admin.controller;

import com.bytx.admin.entity.User;
import com.bytx.admin.util.SFTPUtil;
import com.jcraft.jsch.ChannelSftp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Alex
 * @description 基类描述
 * @date 2018.04.02 22:16
 */
public class BaseController
{
    public static ChannelSftp channelSftp = SFTPUtil.getChannel("47.104.142.179", "root", "BJbytx1234567", 22);

    @Value(value = "${storage.image.path}")
    public String storageImagePath;

    @Value(value = "${access.image.url}")
    public String accessImageUrl;

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
