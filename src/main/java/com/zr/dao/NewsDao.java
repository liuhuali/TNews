package com.zr.dao;

import com.zr.bean.News;
import com.zr.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsDao  extends JpaRepository<News,Long>, JpaSpecificationExecutor<News> {

    @Query("SELECT n FROM News n WHERE n.title LIKE ?1 or n.content like ?1")
    Page<News> findByQuery(String s, Pageable pageable);

    @Query("SELECT n from News n where n.recommend=true")
    List<News> findTop(Pageable pageable);

    @Query("select  function('date_format',n.updateTime,'%Y') as year from News n group by year order by year desc ")
    List<String> findGroupYear();

    @Query("select n from News n where function('date_format',n.updateTime,'%Y') =?1 ")
    List<News> findNewsByYear(String y);
}
