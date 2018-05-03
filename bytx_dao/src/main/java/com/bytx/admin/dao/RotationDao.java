package com.bytx.admin.dao;

import com.bytx.admin.entity.Rotation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotationDao
{
    int selectImagesCount();

    List<Rotation> selectImageRotationByCount(@Param("count") Integer count);

    List<Rotation> selectAllImages();

    Integer addImage(Rotation rotation);

    Integer updateImage(Rotation rotation);
}
