package com.bytx.admin.entity;

import java.io.Serializable;

public class UserRole implements Serializable
{
    private Integer id;
    private Integer uId;
    private Integer rId;

    public UserRole()
    {
    }

    public UserRole(Integer id, Integer uId, Integer rId)
    {
        this.id = id;
        this.uId = uId;
        this.rId = rId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getuId()
    {
        return uId;
    }

    public void setuId(Integer uId)
    {
        this.uId = uId;
    }

    public Integer getrId()
    {
        return rId;
    }

    public void setrId(Integer rId)
    {
        this.rId = rId;
    }

    @Override
    public String toString()
    {
        return "UserRole{" + "id=" + id + ", uId=" + uId + ", rId=" + rId + '}';
    }
}


