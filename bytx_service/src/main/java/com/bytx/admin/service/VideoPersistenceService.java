package com.bytx.admin.service;

import com.bytx.admin.dao.VideoDao;
import com.bytx.admin.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VideoPersistenceService
{
    @Autowired
    private VideoDao videoDao;

    public Integer addVideo(Video video)
    {
        return videoDao.addVideo(video);
    }

    public Integer updateVideo(Video video)
    {
        return videoDao.updateVideo(video);
    }
}
