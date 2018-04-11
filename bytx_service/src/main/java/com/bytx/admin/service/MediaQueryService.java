package com.bytx.admin.service;

import com.bytx.admin.dao.MediaDao;
import com.bytx.admin.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaQueryService
{
    @Autowired
    private MediaDao mediaDao;

    public List<Media> selectMediasByParentId(Integer parentId)
    {
        return mediaDao.selectMediasByParentId(parentId);
    }
}
