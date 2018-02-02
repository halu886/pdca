package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public interface IUserService {
    public User findUserById(String id);
    public void addUser(User user);
    public List<User> getAllUsers();
}
