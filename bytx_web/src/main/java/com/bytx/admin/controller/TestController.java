package com.bytx.admin.controller;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/upload")
    @ResponseBody
    public Integer uploadFile(@RequestParam("testFile") MultipartFile file, HttpServletRequest request) throws IOException
    {
        if (file.isEmpty())
        {
            logger.warn("文件为空");
            return -1;
        }

        String fileName = file.getOriginalFilename();

        String path = SystemUtils.USER_DIR + File.separator + "upload";

        File uploadFile = new File(path + File.separator + fileName);

        if (!uploadFile.getParentFile().exists())
        {
            uploadFile.getParentFile().mkdir();
        }

        file.transferTo(uploadFile);

        return 1;
    }
}
