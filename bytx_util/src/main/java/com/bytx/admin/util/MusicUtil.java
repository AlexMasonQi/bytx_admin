package com.bytx.admin.util;


import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Alex
 * @description 歌曲工具类
 * @date 2018.05.03
 */
public class MusicUtil
{
    private static Logger logger = LoggerFactory.getLogger(MusicUtil.class);

    public static String getMusicTime(File musicFile)
    {
        String time = null;
        try
        {
            MP3File file = (MP3File) AudioFileIO.read(musicFile);
            MP3AudioHeader audioHeader = (MP3AudioHeader) file.getAudioHeader();
            time = audioHeader.getTrackLengthAsString();
            logger.info("歌曲解析成功");
        }
        catch (Exception e)
        {
            logger.error("歌曲解析失败", e);
        }

        return time;
    }

//    public static void main(String[] args)
//    {
//        System.out.println(getMusicTime(new File("E:/test/Avril Lavigne - Innocence.mp3")));
//    }
}
