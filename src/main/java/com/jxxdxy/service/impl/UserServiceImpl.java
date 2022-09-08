package com.jxxdxy.service.impl;

import com.jxxdxy.dao.UserMapper;
import com.jxxdxy.entity.User;
import com.jxxdxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    public boolean queryUserByUsernameAndPassword(String username, String password) {
        return userMapper.queryUserByUsernameAndPassword(username,password) != null;
    }

    public boolean userRegister(User user) {
        return userMapper.userRegister(user) > 0;
    }

    public boolean userUpdate(User user) {
        return userMapper.userUpdate(user) > 0;
    }
}
