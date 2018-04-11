package com.bytx.admin.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable
{
    private Integer id;
    private String menuName;
    private String menuUrl;
    private Integer level;
    private Integer parentId;
    private Integer bshow;
    private Integer bson;
    private Integer secondParentId;
    private List<Menu> menus;

    public Menu(Integer id, String menuName, String menuUrl, Integer level, Integer parentId, Integer bshow, Integer bson, Integer secondParentId, List<Menu> menus)
    {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.level = level;
        this.parentId = parentId;
        this.bshow = bshow;
        this.bson = bson;
        this.secondParentId = secondParentId;
        this.menus = menus;
    }

    public Menu()
    {
    }

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

    public String getMenuUrl()
    {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl)
    {
        this.menuUrl = menuUrl;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public Integer getBshow()
    {
        return bshow;
    }

    public void setBshow(Integer bshow)
    {
        this.bshow = bshow;
    }

    public Integer getBson()
    {
        return bson;
    }

    public void setBson(Integer bson)
    {
        this.bson = bson;
    }

    public Integer getSecondParentId()
    {
        return secondParentId;
    }

    public void setSecondParentId(Integer secondParentId)
    {
        this.secondParentId = secondParentId;
    }

    public List<Menu> getMenus()
    {
        return menus;
    }

    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }

    @Override
    public String toString()
    {
        return "Menu{" + "id=" + id + ", menuName='" + menuName + '\'' + ", menuUrl='" + menuUrl + '\'' + ", level=" + level + ", parentId=" + parentId + ", bshow=" + bshow + ", bson=" + bson + ", secondParentId=" + secondParentId + '}';
    }
}
