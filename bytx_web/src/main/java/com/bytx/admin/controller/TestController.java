package com.bytx.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/upload")
    @ResponseBody
    public Integer uploadFile(HttpServletRequest request) throws IOException
    {
        System.out.println(request.getServletContext().getRealPath("/"));
        MultipartFile testFile1 = ((MultipartHttpServletRequest) request).getFile("testFile1");
        MultipartFile testFile2 = ((MultipartHttpServletRequest) request).getFile("testFile2");

        String testName1 = testFile1.getOriginalFilename();
        String testName2 = testFile2.getOriginalFilename();

        System.out.println(testName1);
        System.out.println(testName2);

        return 1;
    }
}
