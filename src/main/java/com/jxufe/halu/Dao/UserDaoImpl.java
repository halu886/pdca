package com.jxufe.halu.Dao;

import com.jxufe.halu.Mapper.UserMapper;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private UserMapper mapper;

    public UserDaoImpl() {
        String resource = "config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User findUserById(String id) {
        return mapper.findUserById(id);
    }

    public String addUser(User user) {
        mapper.addUser(user);
        session.commit();
        return user.getUserID();
    }

    public List<User> getAllUsers() {
        return mapper.getAllUsers();
    }

    @Override
    public Set<String> getRoleById(String id) {
        return mapper.getRoleById(id);
    }

    @Override
    public Set<String> getPermissionById(String id) {
        return mapper.getPermissionById(id);
    }

    @Override
    public String addRoleOfUser(String userId, String roleId) {
        int id = mapper.addRoleOfUser(userId, roleId);
        session.commit();
        return id + "";
    }

    @Override
    public User findUserByName(String username) {
        return mapper.findUserByName(username);
    }


}
