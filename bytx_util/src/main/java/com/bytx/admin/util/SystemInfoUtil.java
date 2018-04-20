package com.bytx.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemInfoUtil
{
    private static Logger logger = LoggerFactory.getLogger(SystemInfoUtil.class);

    public static List<String> getSystemRomInfo()
    {
        Process pro = null;
        BufferedReader in = null;
        String info = null;
        List<String> systemInfoList = new ArrayList<>();

        try
        {
            Runtime r = Runtime.getRuntime();
            String command = "df -h";
            pro = r.exec(command);
            in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null)
            {
                systemInfoList.add(line);
            }
        }
        catch (IOException e)
        {
            logger.error("获取错误", e);
        }
        finally
        {
            try
            {
                in.close();
                pro.destroy();
            }
            catch (IOException e)
            {
                logger.error("关闭资源错误", e);
            }
        }

        return systemInfoList;
    }
}
