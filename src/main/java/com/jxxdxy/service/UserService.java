package com.jxxdxy.service;

import com.jxxdxy.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    User queryUserById(int id);

    User queryUserByUsername(String username);

    boolean queryUserByUsernameAndPassword(String username, String password);

    boolean userRegister(User user);

    boolean userUpdate(User user);
}
