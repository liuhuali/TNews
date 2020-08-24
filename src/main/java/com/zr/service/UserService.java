package com.zr.service;

import com.zr.bean.User;

public interface UserService {
    User checkUser(String user, String password);
}
