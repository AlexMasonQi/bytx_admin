package com.bytx.admin.util;

import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipOutputStream;

public abstract class ZipUtils
{
    private final static int BUFFER_SIZE = 2028;

    public static void unzipFile(String srcZipFile, String destFilePath) throws IOException
    {
        if (srcZipFile == null || destFilePath == null)
        {
            throw new IllegalArgumentException("illegal unzip file parametes, parameters=" + srcZipFile + ", destFilePath=" + destFilePath);
        }

        File file = new File(srcZipFile);
        if (!file.exists())
        {
            throw new IllegalArgumentException("cannot find srcZipFile=" + srcZipFile);
        }
        File outputDir = new File(destFilePath);
        if (!file.exists())
        {
            file.mkdirs();
        }

        ZipFile zipFile = null;
        try
        {
            zipFile = new ZipFile(file);
            createDirectory(destFilePath, null); // 创建输出目录

            Enumeration<?> enums = zipFile.getEntries();
            while (enums.hasMoreElements())
            {
                ZipEntry entry = (ZipEntry) enums.nextElement();
                if (entry.isDirectory())
                { // 处理目录
                    createDirectory(destFilePath, entry.getName());// 创建空目录
                }
                else
                { // 处理文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    createDirectory(tmpFile.getParent() + "/", null);
                    InputStream in = null;
                    OutputStream out = null;
                    try
                    {
                        in = zipFile.getInputStream(entry);
                        out = new FileOutputStream(tmpFile);
                        int length = 0;
                        byte[] buffer = new byte[BUFFER_SIZE];
                        while ((length = in.read(buffer)) != -1)
                        {
                            out.write(buffer, 0, length);
                        }
                        out.flush();
                    }
                    catch (IOException e)
                    {
                        throw e;
                    }
                    finally
                    {
                        if (in != null)
                        {
                            in.close();
                        }
                        if (out != null)
                        {
                            out.close();
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new IOException("解压缩文件出现异常", e);
        }
        finally
        {
            try
            {
                if (zipFile != null)
                {
                    zipFile.close();
                }
            }
            catch (IOException e)
            {
                throw new IOException("关闭zipFile出现异常", e);
            }
        }

    }

    private static void createDirectory(String outputDir, String subDir)
    {

        if (outputDir == null)
        {
            return;
        }
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals("")))
        { // 子目录不为空
            file = new File(outputDir + "/" + subDir);
        }
        if (!file.exists())
        {
            file.mkdirs();
        }
    }

    public static void unGzipFile(String srcGzipFile, String destFilePath) throws IOException
    {
        if (srcGzipFile == null || destFilePath == null)
        {
            throw new IllegalArgumentException("illegal ungzip file parametes, parameters=" + srcGzipFile + ", destFilePath=" + destFilePath);
        }

        File file = new File(srcGzipFile);
        if (!file.exists())
        {
            throw new IllegalArgumentException("cannot find srcZipFile=" + srcGzipFile);
        }

        GZIPInputStream zin = new GZIPInputStream(new FileInputStream(file));
        OutputStream out = new FileOutputStream(new File(destFilePath));
        try
        {
            byte[] data = new byte[BUFFER_SIZE];
            int len;
            while ((len = zin.read(data)) != -1)
            {
                out.write(data, 0, len);
            }
        }
        finally
        {
            if (null != zin)
            {
                zin.close();
            }
            if (null != out)
            {
                out.close();
            }
        }

    }

    /**
     * @param file 要压缩的文件 stream 目标路径流
     * @return 压缩文件流
     * @description 压缩文件
     * @author Xu Shiqi
     * @date 2018.03.14 09:05
     */
    public static void zip(File file, OutputStream out)
    {
        ZipOutputStream outputStream = null;
        try
        {
            outputStream = new ZipOutputStream(out);
            zipFile(outputStream, file, "");
            if (outputStream != null)
            {
                outputStream.flush();
                outputStream.close();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(ZipUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                outputStream.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(ZipUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void zipFile(ZipOutputStream output, File file, String basePath)
    {
        FileInputStream input = null;
        try
        {
            // 文件为目录
            if (file.isDirectory())
            {
                // 得到当前目录里面的文件列表
                File list[] = file.listFiles();
                basePath = basePath + (basePath.length() == 0 ? "" : "/") + file.getName();
                // 循环递归压缩每个文件
                for (File f : list)
                {
                    zipFile(output, f, basePath);
                }
            }
            else
            {
                // 压缩文件
                basePath = (basePath.length() == 0 ? "" : basePath + "/") + file.getName();
                // System.out.println(basePath);
                output.putNextEntry(new ZipEntry(basePath));
                input = new FileInputStream(file);
                int readLen = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
                {
                    output.write(buffer, 0, readLen);
                    output.flush();
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            // 关闭流
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(ZipUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        File srcFile = new File("E:/test/image");
//        File desFile = new File("F:/zipFileTest/Test1.zip");
//
//        zip(srcFile, desFile);
    }

}
