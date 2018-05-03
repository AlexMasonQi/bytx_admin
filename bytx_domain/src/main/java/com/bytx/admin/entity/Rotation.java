package com.bytx.admin.entity;

import java.io.Serializable;

public class Rotation implements Serializable
{
    private Integer id;
    private String imageUrl;
    private String description;
    private String associateDescription;
    private Integer imageCount;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAssociateDescription()
    {
        return associateDescription;
    }

    public void setAssociateDescription(String associateDescription)
    {
        this.associateDescription = associateDescription;
    }

    public Integer getImageCount()
    {
        return imageCount;
    }

    public void setImageCount(Integer imageCount)
    {
        this.imageCount = imageCount;
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
        return "Rotation{" + "id=" + id + ", imageUrl='" + imageUrl + '\'' + ", description='" + description + '\'' + ", associateDescription='" + associateDescription + '\'' + ", imageCount=" + imageCount + ", status=" + status + '}';
    }
}
