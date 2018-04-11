package com.bytx.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable
{
    private Integer id;
    private String userName;
    private String userPassword;
    private String salt;
    private Integer userRole;
    private LocalDateTime addTime;
    private LocalDateTime lastLoginTime;
    private Integer status;

    public User()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Integer getUserRole()
    {
        return userRole;
    }

    public void setUserRole(Integer userRole)
    {
        this.userRole = userRole;
    }

    public LocalDateTime getAddTime()
    {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime)
    {
        this.addTime = addTime;
    }

    public LocalDateTime getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", userPassword='" + userPassword + '\'' + ", salt='" + salt + '\'' + ", userRole=" + userRole + ", addTime=" + addTime + ", lastLoginTime=" + lastLoginTime + ", status=" + status + '}';
    }
}
