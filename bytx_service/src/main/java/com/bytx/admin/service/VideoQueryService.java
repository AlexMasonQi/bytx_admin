package com.bytx.admin.service;

import com.bytx.admin.dao.VideoDao;
import com.bytx.admin.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoQueryService
{
    @Autowired
    private VideoDao videoDao;

    public Video selectVideoById(Integer id)
    {
        return videoDao.selectVideoById(id);
    }

    public List<Video> selectAllVideos()
    {
        return videoDao.selectAllVideos();
    }
}
