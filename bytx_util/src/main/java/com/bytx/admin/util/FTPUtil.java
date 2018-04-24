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
            result = upload(ftpClient, in, desFileName, desPath);
        }
        catch (IOException e)
        {
            logger.error("文件上传失败", e);
        }

        return result;
    }

    private static Boolean upload(FTPClient ftpClient, FileInputStream in, String desFileName, String desPath)
    {
        ftpClient.setControlEncoding("utf-8");
        Boolean isSuccess = false;

        try
        {
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            //判断服务器相关目录是否存在
            if (!isFileExist(ftpClient, desPath))
            {
                createDirectory(ftpClient, desPath);
            }

            ftpClient.changeWorkingDirectory(desPath);
            isSuccess = ftpClient.storeFile(desFileName, in);
        }
        catch (IOException e)
        {
            logger.error("upload failed", e);
        }
        finally
        {
            try
            {
                in.close();
                ftpClient.logout();

                if (ftpClient.isConnected())
                {
                    ftpClient.disconnect();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    /**
     * @param ftpClient FTP客户端对象
     * @param directory 要切换的目录
     * @return true 切换成功;false 切换失败
     * @description 切换目录
     * @author Alex
     * @date 2018.04.24 22:56
     */
    private static Boolean changeWorkingDirectory(FTPClient ftpClient, String directory)
    {
        Boolean flag = false;

        try
        {
            flag = ftpClient.changeWorkingDirectory(directory);

            if (flag)
            {
                logger.info("进入文件夹=======>成功");
            }
            else
            {
                logger.error("未进入文件夹");
            }
        }
        catch (IOException e)
        {
            logger.error("切换目录异常", e);
        }

        return flag;
    }

    private static Boolean createDirectory(FTPClient ftpClient, String path)
    {
        Boolean result = false;
        try
        {
            result = ftpClient.makeDirectory(path);
        }
        catch (IOException e)
        {
            logger.error("创建目录失败", e);
        }

        return result;
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

    public static void main(String[] args)
    {
        FTPClient ftpClient = connectServer("140.143.203.132", 21, "alex", "becauseofyou7");

        Boolean isSuccess = uploadFile(ftpClient, "E:/test/user.sql", "user.sql", "/usr/test");

        if (isSuccess)
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("Failed");
        }
    }
}
