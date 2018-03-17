package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;
import java.util.Set;

public interface IUserDao {
    public User findUserById(String id);//查询
    public void addUser(User user);//添加
    public List<User> getAllUsers();
    public Set<String> getRoleById(String id);
    public Set<String> getPermissionById(String id);
}
