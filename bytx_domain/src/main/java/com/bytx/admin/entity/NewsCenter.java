package com.bytx.admin.entity;

import java.io.Serializable;

public class NewsCenter implements Serializable
{
    private Integer id;

    private Integer newsId;

    private String introduction;

    private String content;

    private String imageSrc;

    private Integer parentId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getNewsId()
    {
        return newsId;
    }

    public void setNewsId(Integer newsId)
    {
        this.newsId = newsId;
    }

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getImageSrc()
    {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc)
    {
        this.imageSrc = imageSrc;
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
        return "NewsCenter{" + "id=" + id + ", newsId=" + newsId + ", introduction='" + introduction + '\'' + ", content='" + content + '\'' + ", imageSrc='" + imageSrc + '\'' + ", parentId=" + parentId + '}';
    }
}
