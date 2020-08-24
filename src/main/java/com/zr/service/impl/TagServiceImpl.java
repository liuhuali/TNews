package com.zr.service.impl;

import com.zr.bean.Tag;
import com.zr.dao.TagDao;
import com.zr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public Tag findById(Long id) {
        return tagDao.getOne(id);
    }

    @Override
    public void input(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    public void deleteById(Long id) {
        tagDao.deleteById(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> findByIds(String tagIds) {
        List<Long> ids=new ArrayList<>();
        if(!StringUtils.isEmpty(tagIds)){
            String[] strings = tagIds.split(",");
            for(String s:strings){
                ids.add(new Long(s));
            }
        }
        return tagDao.findAllById(ids);
    }

    @Override
    public List<Tag> findTop(int i) {
        Sort sort= Sort.by(Sort.Direction.DESC,"newsList.size");
        Pageable pageable=PageRequest.of(0,i,sort);
        return tagDao.findTop(pageable);
    }
}
