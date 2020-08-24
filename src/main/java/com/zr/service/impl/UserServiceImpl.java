package com.zr.service.impl;

import com.zr.bean.User;
import com.zr.dao.UserDao;
import com.zr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }
}
