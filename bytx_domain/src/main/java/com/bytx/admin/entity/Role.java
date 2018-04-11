package com.bytx.admin.entity;

import java.io.Serializable;

public class Role implements Serializable
{
    private Integer id;
    private String roleName;
    private String roleType;

    public Role()
    {
    }

    public Role(Integer id, String roleName, String roleType)
    {
        this.id = id;
        this.roleName = roleName;
        this.roleType = roleType;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleType()
    {
        return roleType;
    }

    public void setRoleType(String roleType)
    {
        this.roleType = roleType;
    }

    @Override
    public String toString()
    {
        return "Role{" + "id=" + id + ", roleName='" + roleName + '\'' + ", roleType='" + roleType + '\'' + '}';
    }
}
