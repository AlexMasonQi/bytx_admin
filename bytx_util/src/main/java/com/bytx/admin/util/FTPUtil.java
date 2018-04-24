package com.bytx.admin.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Alex
 * @description FTP工具类
 * @date 2018.04.24 13:25
 */
public class FTPUtil
{
    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    /**
     * @param hostIP   IP地址
     * @param port     端口号
     * @param userName 用户名
     * @param password 密码
     * @description 连接ftp服务器
     * @author Alex
     * @date 2018.04.24 14:06
     */
    public static FTPClient connectServer(String hostIP, Integer port, String userName, String password)
    {
        FTPClient ftpClient = new FTPClient();
        try
        {
            Integer reply = 0;

            ftpClient.connect(hostIP, port);
            ftpClient.login(userName, password);

            reply = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                logger.error("无法连接FTP服务器");
                ftpClient.disconnect();
                return null;
            }
            else
            {
                logger.info("======连接成功======");
            }
        }
        catch (IOException e)
        {
            logger.error("连接失败", e);
        }

        return ftpClient;
    }

    /**
     * @param ftpClient        FTPClient对象
     * @param originalFilePath 源文件路径
     * @param desFileName      目标文件名
     * @param desPath          目标文件目录
     * @return true代表上传成功，false代表上传失败
     * @description 向FTP服务器中上传文件
     * @author Alex
     * @date 2018.04.24 14:13
     */
    public static Boolean uploadFile(FTPClient ftpClient, String originalFilePath, String desFileName, String desPath)
    {
        FileInputStream in = null;
        Boolean result = false;

        try
        {
            in = new FileInputStream(new File(originalFilePath));
            Boolean tag = upload(ftpClient, in, desFileName, desPath);
        }
        catch (IOException e)
        {
            logger.error("文件上传失败", e);
            result = false;
        }

        return result;
    }

    private static Boolean upload(FTPClient ftpClient, FileInputStream in, String desFileName, String desPath)
    {
        ftpClient.setControlEncoding("utf-8");

        try
        {
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            //判断服务器相关目录是否存在
            if (!isFileExist(ftpClient, desPath))
            {

            }

            ftpClient.changeWorkingDirectory(desPath);


        }
        catch (IOException e)
        {
            logger.error("upload failed", e);
        }

        return false;
    }

    /**
     * @description 判断文件是否存在
     * @author Alex
     * @date 2018.04.24 14:30
     */
    private static Boolean isFileExist(FTPClient ftpClient, String path) throws IOException
    {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0)
        {
            flag = true;
        }
        return flag;
    }
}
