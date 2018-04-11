package com.bytx.admin.entity;

import java.io.Serializable;

public class BasicInfo implements Serializable
{
    private Integer id;
    private String basicLogo;
    private String basicQrcode;
    private String basicAddress;
    private String basicPhone;
    private String basicEmail;
    private String basicRecord;
    private Integer status;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBasicLogo()
    {
        return basicLogo;
    }

    public void setBasicLogo(String basicLogo)
    {
        this.basicLogo = basicLogo;
    }

    public String getBasicQrcode()
    {
        return basicQrcode;
    }

    public void setBasicQrcode(String basicQrcode)
    {
        this.basicQrcode = basicQrcode;
    }

    public String getBasicAddress()
    {
        return basicAddress;
    }

    public void setBasicAddress(String basicAddress)
    {
        this.basicAddress = basicAddress;
    }

    public String getBasicPhone()
    {
        return basicPhone;
    }

    public void setBasicPhone(String basicPhone)
    {
        this.basicPhone = basicPhone;
    }

    public String getBasicEmail()
    {
        return basicEmail;
    }

    public void setBasicEmail(String basicEmail)
    {
        this.basicEmail = basicEmail;
    }

    public String getBasicRecord()
    {
        return basicRecord;
    }

    public void setBasicRecord(String basicRecord)
    {
        this.basicRecord = basicRecord;
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
        return "BasicInfo{" + "id=" + id + ", basicLogo='" + basicLogo + '\'' + ", basicQrcode='" + basicQrcode + '\'' + ", basicAddress='" + basicAddress + '\'' + ", basicPhone='" + basicPhone + '\'' + ", basicEmail='" + basicEmail + '\'' + ", basicRecord='" + basicRecord + '\'' + ", status=" + status + '}';
    }
}
