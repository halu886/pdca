package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public interface IUserDao {
    public User findUserById(String id);//查询
    public void addUser(User user);//添加
    public List<User> getAllUsers();
    public List<Project> getProjectOfUser(String id);
}
