package com.bytx.admin.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Alex
 * @description SFTP传输工具类
 * @date 2018.04.25 09:00
 */
public class SFTPUtil
{
    private static Logger logger = LoggerFactory.getLogger(SFTPUtil.class);

    private static Session session;
    private static Channel channel;

    /**
     * @param host     主机IP
     * @param username 用户名
     * @param password 密码
     * @param port     端口号
     * @return ChannelSftp对象
     * @description 建立连接
     * @author Alex
     * @date 2018.04.25 09:35
     */
    public static ChannelSftp getChannel(String host, String username, String password, Integer port)
    {
        //创建一个JSch对象
        JSch jsch = new JSch();

        try
        {
            //通过主机IP，端口号，用户名和密码获取session对象
            session = jsch.getSession(username, host, port);
            logger.debug("session creating...");

            //设置连接密码
            if (password != null && !"".equals(password))
            {
                session.setPassword(password);
            }

            //为session设置配置信息
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            //设置连接超时时间
            session.setTimeout(60000);
            session.connect();
            logger.debug("session connected!");

            //通过session打开sftp通道
            logger.debug("opening channel...");
            channel = session.openChannel("sftp");
            channel.connect();

            logger.debug("Connected successfully:\nftpHost = " + host + "\nftpUserName = " + username + "\nreturning: " + channel);
        }
        catch (JSchException e)
        {
            logger.error("连接失败", e);
        }

        return (ChannelSftp) channel;
    }

    /**
     * @param channelSftp  当前ChannelSftp对象
     * @param originalFile 源文件
     * @param desPath      目标目录
     * @description 上传文件
     * @author Alex
     * @date 2018.04.25 14:05
     */
    public static void uploadFile(ChannelSftp channelSftp, String originalFile, String desPath)
    {
        try
        {
            channelSftp.cd("/");

            //递归创建目录，如果不存在就创建
            String[] folderArr = desPath.split("/");
            for (var i = 0; i < folderArr.length; i++)
            {
                if (folderArr[i].length() > 0)
                {
                    try
                    {
                        channelSftp.cd(folderArr[i]);
                    }
                    catch (SftpException err)
                    {
                        channelSftp.mkdir(folderArr[i]);
                        channelSftp.cd(folderArr[i]);
                    }
                }
            }
            channelSftp.put(originalFile, desPath);
        }
        catch (SftpException e)
        {
            logger.error("传输失败", e);
        }
    }

    /**
     * @param channelSftp 当前ChannelSftp对象
     * @description 关闭连接
     * @author Alex
     * @date 2018.04.25 14:56
     */
    public static void closeConnection(ChannelSftp channelSftp) throws JSchException
    {
        if (channelSftp != null)
        {
            channelSftp.disconnect();
        }
        if (session != null)
        {
            session.disconnect();
        }
    }

//    public static void main(String[] args)
//    {
//        ChannelSftp channelSftp = getChannel("47.104.142.179", "root", "BJbytx1234567", 22);
//        uploadFile(channelSftp, "D:/test/test.png", "/data/wwwroot/default/upload/ckeditor/companyInfo/images");
//        try
//        {
//            closeConnection(channelSftp);
//        }
//        catch (JSchException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
