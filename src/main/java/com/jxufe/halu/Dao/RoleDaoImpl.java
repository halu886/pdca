package com.jxufe.halu.Dao;

import com.jxufe.halu.Mapper.RoleMapper;
import com.jxufe.halu.model.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class RoleDaoImpl implements IRoleDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private RoleMapper mapper;

    public RoleDaoImpl() {
        String resource = "config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(RoleMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role findRoleById(String id) {
        return null;
    }

    @Override
    public Role findRoleByName(String name) {
        return mapper.findRoleByName(name);
    }
}
