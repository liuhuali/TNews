package com.zr.service;

import com.zr.bean.News;
import com.zr.bean.NewsQuery;
import com.zr.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface NewsService {
    Page<News> findAll(Pageable pageable);

    News findById(Long id);

    void add(News news);

    String findTagIds(List<Tag> tags);

    Page<News> searchNews(Pageable pageable, NewsQuery newsQuery);

    Page<News> findAll(String query, Pageable pageable);

    Page<News> searchNews(Pageable pageable, Long  id);

    List<News> findTop(int i);

    void deleteById(Long id);

    HashMap<String, List<News>> getMap();

    Long getCount();
}
