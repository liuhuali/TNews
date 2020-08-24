package com.zr.service;

import com.zr.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {
    Page<Tag> findAll(Pageable pageable);

    Tag findById(Long id);

    void input(Tag tag);

    void deleteById(Long id);

    List<Tag> findAll();

    List<Tag> findByIds(String tagIds);

    List<Tag> findTop(int i);
}
