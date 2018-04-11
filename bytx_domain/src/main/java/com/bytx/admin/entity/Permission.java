package com.bytx.admin.entity;

import java.io.Serializable;

public class Permission implements Serializable
{
    private Integer id;
    private String permissionName;
    private String permissionUrl;

    public Permission()
    {
    }

    public Permission(Integer id, String permissionName, String permissionUrl)
    {
        this.id = id;
        this.permissionName = permissionName;
        this.permissionUrl = permissionUrl;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getPermissionName()
    {
        return permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl()
    {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl)
    {
        this.permissionUrl = permissionUrl;
    }

    @Override
    public String toString()
    {
        return "Permission{" + "id=" + id + ", permissionName='" + permissionName + '\'' + ", permissionUrl='" + permissionUrl + '\'' + '}';
    }
}
