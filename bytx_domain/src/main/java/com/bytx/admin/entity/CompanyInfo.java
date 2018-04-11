package com.bytx.admin.entity;

import java.io.Serializable;

public class CompanyInfo implements Serializable
{
    private Integer id;

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
        return "CompanyInfo{" + "id=" + id + ", content='" + content + '\'' + ", parentId=" + parentId + '}';
    }
}
