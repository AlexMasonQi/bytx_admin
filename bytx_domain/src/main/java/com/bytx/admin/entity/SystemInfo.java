package com.bytx.admin.entity;

import java.io.Serializable;

public class SystemInfo implements Serializable
{
    private String fileSystem;
    private String size;
    private String used;
    private String availible;
    private String usePercentage;
    private String mountedOn;

    public SystemInfo(String fileSystem, String size, String used, String availible, String usePercentage, String mountedOn)
    {
        this.fileSystem = fileSystem;
        this.size = size;
        this.used = used;
        this.availible = availible;
        this.usePercentage = usePercentage;
        this.mountedOn = mountedOn;
    }

    public SystemInfo()
    {
    }

    public String getFileSystem()
    {
        return fileSystem;
    }

    public void setFileSystem(String fileSystem)
    {
        this.fileSystem = fileSystem;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getUsed()
    {
        return used;
    }

    public void setUsed(String used)
    {
        this.used = used;
    }

    public String getAvailible()
    {
        return availible;
    }

    public void setAvailible(String availible)
    {
        this.availible = availible;
    }

    public String getUsePercentage()
    {
        return usePercentage;
    }

    public void setUsePercentage(String usePercentage)
    {
        this.usePercentage = usePercentage;
    }

    public String getMountedOn()
    {
        return mountedOn;
    }

    public void setMountedOn(String mountedOn)
    {
        this.mountedOn = mountedOn;
    }

    @Override
    public String toString()
    {
        return "SystemInfo{" + "fileSystem='" + fileSystem + '\'' + ", size='" + size + '\'' + ", used='" + used + '\'' + ", availible='" + availible + '\'' + ", usePercentage='" + usePercentage + '\'' + ", mountedOn='" + mountedOn + '\'' + '}';
    }
}
