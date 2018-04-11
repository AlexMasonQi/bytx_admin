package com.bytx.admin.service;

import com.bytx.admin.dao.NewsCenterDao;
import com.bytx.admin.entity.NewsCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCenterQueryService
{
    @Autowired
    private NewsCenterDao newsCenterDao;

    public List<NewsCenter> selectNewsById(Integer id)
    {
        return newsCenterDao.selectNewsCenterById(id);
    }

    public NewsCenter selectNewsByNewsId(Integer newsId)
    {
        return newsCenterDao.selectNewsByNewsId(newsId);
    }

    public List<NewsCenter> selectThreeNews()
    {
        return newsCenterDao.selectthreeNews();
    }
}
