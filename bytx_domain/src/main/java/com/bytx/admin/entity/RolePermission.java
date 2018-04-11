package com.bytx.admin.entity;

import java.io.Serializable;

public class RolePermission implements Serializable
{
    private Integer id;
    private Integer rId;
    private Integer pId;

    public RolePermission()
    {
    }

    public RolePermission(Integer id, Integer rId, Integer pId)
    {
        this.id = id;
        this.rId = rId;
        this.pId = pId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getrId()
    {
        return rId;
    }

    public void setrId(Integer rId)
    {
        this.rId = rId;
    }

    public Integer getpId()
    {
        return pId;
    }

    public void setpId(Integer pId)
    {
        this.pId = pId;
    }

    @Override
    public String toString()
    {
        return "RolePermission{" + "id=" + id + ", rId=" + rId + ", pId=" + pId + '}';
    }
}
