package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IRoleDao;
import com.jxufe.halu.Dao.IUserDao;
import com.jxufe.halu.Dao.RoleDaoImpl;
import com.jxufe.halu.Dao.UserDaoImpl;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.Role;
import com.jxufe.halu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        roleDao = new RoleDaoImpl();
    }

    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    public String addUser(User user) {
        String id = userDao.addUser(user);
        return id;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Set<String> queryRoleByID(String userId) {
        return userDao.getRoleById(userId);
    }

    @Override
    public Set<String> queryPermissionByID(String userId) {
        return userDao.getPermissionById(userId);
    }

    @Override
    public String addRoleOfUser(String userId, String roleId) {
        return  userDao.addRoleOfUser(userId,roleId);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public Map countOfIndex(String userID) {
        Map count = new HashMap();
        Map countProject =  userDao.countOfProject(userID);
        Map countTask = userDao.countOfTask(userID);
        count.put("project",countProject);
        count.put("task",countTask);
        return count;
    }

    @Override
    public List<User> findUserByRole(String normalUser) {
        return userDao.findUserByRole(normalUser);
    }

    @Override
    public boolean register(User user, String roleName) {
        String userId = this.addUser(user);
        Role role = roleDao.findRoleByName(roleName);
        String roleId = this.addRoleOfUser(userId, role.getRoleId());
        if (roleId instanceof String) {
            return true;
        }
        return false;
    }


}
