package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IUserDao;
import com.jxufe.halu.Dao.UserDaoImpl;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public class UserServiceImpl implements IUserService{
    private IUserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User findUserById(String id){
        return  userDao.findUserById(id);
    }

    public void addUser(User user){
        userDao.addUser(user);
    }

    public List<User> getAllUsers(){
        return  userDao.getAllUsers();
    }


}
