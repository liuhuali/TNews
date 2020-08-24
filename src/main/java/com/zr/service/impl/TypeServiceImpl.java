package com.zr.service.impl;

import com.zr.bean.Type;
import com.zr.dao.TypeDao;
import com.zr.service.TypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Override
    @RequiresPermissions(value = "user—delete")
    public void delteById(Long id) {
        typeDao.deleteById(id);
    }

    @Override
    @RequiresRoles(value = "admin")
    public void add(Type type) {
        typeDao.save(type);
    }

    @Override
    public Type findById(Long id) {
        return typeDao.getOne(id);
    }

    @Override
    public List<Type> findAll() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> findTop(int i) {
        Sort sort = Sort.by(Sort.Direction.DESC, "newsList.size");
        Pageable pageable = PageRequest.of(0,i,sort);
        return typeDao.findTop(pageable);
    }
}
