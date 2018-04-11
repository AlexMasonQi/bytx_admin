package com.bytx.admin.service;

import com.bytx.admin.dao.MusicDao;
import com.bytx.admin.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicQueryService
{
    @Autowired
    private MusicDao musicDao;

    public Music selectSongsById(Integer musicId)
    {
        return musicDao.selectSongsById(musicId);
    }

    public List<Music> selectAllSongs()
    {
        return musicDao.selectAllSongs();
    }
}
