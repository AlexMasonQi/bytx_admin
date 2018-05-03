package com.bytx.admin.service;

import com.bytx.admin.dao.MusicDao;
import com.bytx.admin.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MusicPersistenceService
{
    @Autowired
    private MusicDao musicDao;

    public Integer addMusic(Music music)
    {
        return musicDao.addMusic(music);
    }

    public Integer updateMusicInfoById(Music music)
    {
        return musicDao.updateMusicInfoById(music);
    }
}
