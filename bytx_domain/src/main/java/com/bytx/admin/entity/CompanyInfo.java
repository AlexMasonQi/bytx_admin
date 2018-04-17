package com.bytx.admin.entity;

import java.io.Serializable;

public class CompanyInfo implements Serializable
{
    private Integer id;

    private String menuName;

    private String content;

    private Integer parentId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    @Override
    public String toString()
    {
        return "CompanyInfo{" + "id=" + id + ", menuName='" + menuName + '\'' + ", content='" + content + '\'' + ", parentId=" + parentId + '}';
    }
}
