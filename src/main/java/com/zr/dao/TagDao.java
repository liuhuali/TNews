package com.zr.dao;

import com.zr.bean.Tag;
import com.zr.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagDao  extends JpaRepository<Tag,Long> {

    @Query("SELECT t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
