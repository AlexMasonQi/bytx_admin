package com.bytx.admin.service;

import com.bytx.admin.dao.NewsCenterDao;
import com.bytx.admin.entity.NewsCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsCenterPersistenceService
{
    @Autowired
    private NewsCenterDao newsCenterDao;

    public Integer addNews(NewsCenter newsCenter)
    {
        return newsCenterDao.addNews(newsCenter);
    }

    public Integer updateNews(NewsCenter newsCenter)
    {
        return newsCenterDao.updateNewsById(newsCenter);
    }
}
