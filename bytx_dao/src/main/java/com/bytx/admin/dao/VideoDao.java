package com.bytx.admin.dao;

import com.bytx.admin.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao
{
    Video selectVideoById(@Param("id") Integer id);

    List<Video> selectAllVideos();

    Integer addVideo(Video video);

    Integer updateVideo(Video video);
}
