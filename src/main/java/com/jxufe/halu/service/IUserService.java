package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface IUserService {
    public User findUserById(String id);
    public void addUser(User user);
    public List<User> getAllUsers();
    Set<String> queryRoleByID(String userId);
    Set<String> queryPermissionByID(String userId);
}
