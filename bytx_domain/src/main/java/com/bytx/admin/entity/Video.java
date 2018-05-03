package com.bytx.admin.entity;

import java.io.Serializable;

public class Video implements Serializable
{
    private Integer id;
    private String videoPath;
    private String videoTitle;
    private String videoContent;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getVideoPath()
    {
        return videoPath;
    }

    public void setVideoPath(String videoPath)
    {
        this.videoPath = videoPath;
    }

    public String getVideoTitle()
    {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle)
    {
        this.videoTitle = videoTitle;
    }

    public String getVideoContent()
    {
        return videoContent;
    }

    public void setVideoContent(String videoContent)
    {
        this.videoContent = videoContent;
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
        return "Video{" + "id=" + id + ", videoPath='" + videoPath + '\'' + ", videoTitle='" + videoTitle + '\'' + ", videoContent='" + videoContent + '\'' + ", status=" + status + '}';
    }
}
