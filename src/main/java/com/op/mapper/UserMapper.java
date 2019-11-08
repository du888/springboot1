package com.op.mapper;

import com.op.pojo.User;

import java.util.List;

public interface UserMapper {
    public String getUpwd(String uname);

    public String getRole(String uname);

    public int addUser(User user);

    public int deleteUser(int uid);

    public User getUser(int uid);

    public int updateUser(User user);

    public List<User> getUserList();
}
