package com.bytx.admin.entity;

import java.io.Serializable;

public class Music implements Serializable
{
    private Integer id;
    private String musicPath;
    private String musicName;
    private String musicSinger;
    private String musicTime;
    private String musicLrcPath;
    private String musicImagesPath;
    private String musicImagesCount;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMusicPath()
    {
        return musicPath;
    }

    public void setMusicPath(String musicPath)
    {
        this.musicPath = musicPath;
    }

    public String getMusicName()
    {
        return musicName;
    }

    public void setMusicName(String musicName)
    {
        this.musicName = musicName;
    }

    public String getMusicSinger()
    {
        return musicSinger;
    }

    public void setMusicSinger(String musicSinger)
    {
        this.musicSinger = musicSinger;
    }

    public String getMusicTime()
    {
        return musicTime;
    }

    public void setMusicTime(String musicTime)
    {
        this.musicTime = musicTime;
    }

    public String getMusicLrcPath()
    {
        return musicLrcPath;
    }

    public void setMusicLrcPath(String musicLrcPath)
    {
        this.musicLrcPath = musicLrcPath;
    }

    public String getMusicImagesPath()
    {
        return musicImagesPath;
    }

    public void setMusicImagesPath(String musicImagesPath)
    {
        this.musicImagesPath = musicImagesPath;
    }

    public String getMusicImagesCount()
    {
        return musicImagesCount;
    }

    public void setMusicImagesCount(String musicImagesCount)
    {
        this.musicImagesCount = musicImagesCount;
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
        return "Music{" + "id=" + id + ", musicPath='" + musicPath + '\'' + ", musicName='" + musicName + '\'' + ", musicSinger='" + musicSinger + '\'' + ", musicTime='" + musicTime + '\'' + ", musicLrcPath='" + musicLrcPath + '\'' + ", musicImagesPath='" + musicImagesPath + '\'' + ", musicImagesCount='" + musicImagesCount + '\'' + ", status=" + status + '}';
    }
}
