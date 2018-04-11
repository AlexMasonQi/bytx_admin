package com.bytx.admin.dao;

import com.bytx.admin.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicDao
{
    Music selectSongsById(@Param("id") Integer id);

    List<Music> selectAllSongs();
}
