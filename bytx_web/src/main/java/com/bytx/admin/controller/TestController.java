package com.bytx.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RequestMapping("/test")
public class TestController extends BaseController
{
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    @ResponseBody
    public Integer uploadFile(MultipartFile upload_file, HttpServletRequest request) throws IOException
    {
        String fileName = upload_file.getOriginalFilename();

        String path = request.getServletContext().getRealPath("/") + "/images";

        File uploadFile = new File(path + File.separator + fileName);

        if (!uploadFile.getParentFile().exists())
        {
            uploadFile.getParentFile().mkdir();
        }

        upload_file.transferTo(uploadFile);

        return 1;
    }
}
