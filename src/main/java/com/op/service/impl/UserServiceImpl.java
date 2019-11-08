package com.op.service.impl;

import com.op.mapper.UserMapper;
import com.op.pojo.User;
import com.op.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getUpwd(String uname)
    {
        return userMapper.getUpwd(uname);
    }

    @Override
    public String getRole(String uname) {

        return userMapper.getRole(uname);
    }

    @Override
    public int addUser(User user) {

        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(int uid) {

        return userMapper.deleteUser(uid);
    }

    @Override
    public User getUser(int uid) {

        return userMapper.getUser(uid);
    }

    @Override
    public int updateUser(User user) {

        return userMapper.updateUser(user);
    }

    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }
}
