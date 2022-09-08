package com.jxxdxy.dao;

import com.jxxdxy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User queryUserById(int id);

    User queryUserByUsername(String username);

    User queryUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    int userRegister(User user);

    int userUpdate(User user);
}
