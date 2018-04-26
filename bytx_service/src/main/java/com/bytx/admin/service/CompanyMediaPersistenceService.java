package com.bytx.admin.service;

import com.bytx.admin.dao.MediaDao;
import com.bytx.admin.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyMediaPersistenceService
{
    @Autowired
    private MediaDao mediaDao;

    @Autowired
    private MediaQueryService mediaQueryService;

    public Integer updateMedia(Media media)
    {
        Integer parentId = mediaQueryService.selectParentIdById(media.getId());
        media.setParentId(parentId);

        return mediaDao.updateMedia(media);
    }
}
