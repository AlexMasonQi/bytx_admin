package com.bytx.admin.entity;

import java.io.Serializable;

public class Media implements Serializable
{
    private Integer id;
    private Integer parentId;
    private Integer mediaId;
    private String name;
    private String content;
    private String images;
    private Integer tag;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public Integer getMediaId()
    {
        return mediaId;
    }

    public void setMediaId(Integer mediaId)
    {
        this.mediaId = mediaId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getImages()
    {
        return images;
    }

    public void setImages(String images)
    {
        this.images = images;
    }

    public Integer getTag()
    {
        return tag;
    }

    public void setTag(Integer tag)
    {
        this.tag = tag;
    }

    @Override
    public String toString()
    {
        return "Media{" + "id=" + id + ", parentId=" + parentId + ", mediaId=" + mediaId + ", name='" + name + '\'' + ", content='" + content + '\'' + ", images='" + images + '\'' + ", tag=" + tag + '}';
    }
}
