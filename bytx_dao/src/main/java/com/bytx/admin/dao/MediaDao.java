package com.bytx.admin.dao;

import com.bytx.admin.entity.Media;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaDao
{
    List<Media> selectMediasByParentId(@Param("parentId") Integer parentId);

    List<Media> selectAllMedia();

    Integer selectParentIdById(@Param("id") Integer id);

    Integer updateMedia(Media media);
}
