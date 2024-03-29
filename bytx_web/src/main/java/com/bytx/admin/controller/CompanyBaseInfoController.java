package com.bytx.admin.controller;

import com.bytx.admin.entity.BasicInfo;
import com.bytx.admin.service.BasicInfoQueryService;
import com.bytx.admin.service.CompanyBaseInfoPersistenceService;
import com.bytx.admin.util.SFTPUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/info")
public class CompanyBaseInfoController extends BaseController
{
    @Autowired
    private CompanyBaseInfoPersistenceService companyBaseInfoPersistenceService;

    @Autowired
    private BasicInfoQueryService basicInfoQueryService;

    @RequestMapping("/showBaseInfo")
    public String showBaseInfo(Map model)
    {
        BasicInfo basicInfo = basicInfoQueryService.selectBasicInfoByStatus();

        model.put("basicInfo", basicInfo);

        return "admin/companybaseinfo";
    }

    @RequestMapping("/updateBasicInfo")
    @ResponseBody
    public Integer updateCompanyBaseInfo(BasicInfo basicInfo, HttpServletRequest request)
    {
        basicInfo.setId(1);

        String basePath = request.getServletContext().getRealPath("/");

        MultipartFile logoFile = ((MultipartHttpServletRequest) request).getFile("logo");
        MultipartFile codeFile = ((MultipartHttpServletRequest) request).getFile("code");

        if (!Objects.isNull(logoFile) && !Objects.isNull(codeFile))
        {
            String logoName = logoFile.getOriginalFilename();

            String codeName = codeFile.getOriginalFilename();

            String rootPath = basePath + storageImagePath + "/" + "basic_info";
            String logoPath = rootPath + "/" + "logo" + "/" + logoName;
            String codePath = rootPath + "/" + "qrcode" + "/" + codeName;

            String remoteLogoPath = "/data/wwwroot/default" + storageImagePath + "/basic_info/logo";
            String remoteLogoUrl = accessImageUrl + storageImagePath + "/basic_info/logo/" + logoName;
            String remoteCodePath = "/data/wwwroot/default" + storageImagePath + "/basic_info/qrcode";
            String remoteCodeUrl = accessImageUrl + storageImagePath + "/basic_info/qrcode/" + codeName;

            File lFile = new File(logoPath);
            File qFile = new File(codePath);

            try
            {
                FileUtils.forceMkdir(lFile.getParentFile());
                FileUtils.forceMkdir(qFile.getParentFile());

                logoFile.transferTo(lFile);
                codeFile.transferTo(qFile);

                SFTPUtil.uploadFile(BaseController.channelSftp, logoPath, remoteLogoPath);
                SFTPUtil.uploadFile(BaseController.channelSftp, codePath, remoteCodePath);

                basicInfo.setBasicLogo(remoteLogoUrl);
                basicInfo.setBasicQrcode(remoteCodeUrl);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Integer tag = companyBaseInfoPersistenceService.updateCompanyBaseInfo(basicInfo);
        System.out.println(tag);
        return tag;
    }
}
