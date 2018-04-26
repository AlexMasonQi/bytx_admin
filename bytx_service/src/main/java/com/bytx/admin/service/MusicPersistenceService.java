package com.bytx.admin.service;

import com.bytx.admin.dao.MusicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MusicPersistenceService
{
    @Autowired
    private MusicDao musicDao;
}
