package com.zr.service;

import com.zr.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {
    Page<Type> findAll(Pageable pageable);

    void delteById(Long id);

    void add(Type type);

    Type findById(Long id);

    List<Type> findAll();

    List<Type> findTop(int i);
}
