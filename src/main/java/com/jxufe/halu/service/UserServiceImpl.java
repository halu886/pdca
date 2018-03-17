package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IUserDao;
import com.jxufe.halu.Dao.UserDaoImpl;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements IUserService{

    @Autowired
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

    @Override
    public Set<String> queryRoleByID(String userId) {
        return userDao.getRoleById(userId);
    }

    @Override
    public Set<String> queryPermissionByID(String userId) {
        return userDao.getPermissionById(userId);
    }


}
