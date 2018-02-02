package com.jxufe.halu.Dao;

import com.jxufe.halu.Mapper.ProjectMapper;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ProjectDaoImpl  implements  IProjectDao{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private ProjectMapper mapper;

    public ProjectDaoImpl() {
        String resource  = "config.xml";
        try{
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory  = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(ProjectMapper.class);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Project findProjectById(String id) {
        return mapper.findProjectById(id);
    }

    public void addProject(Project project) {
        mapper.addProject(project);
        session.commit();
    }


    public List<Project> getAllProjects() {
        List<Project> list = mapper.getAllProject();
        return list;
    }

    public int update(Project project){
     return  mapper.update(project);
    }

    public void addAssoiateUser(User user,Project project){
        mapper.addAssoiateUser(project,user);
        session.commit();
    }
    public List<Project> getProjectOfUser(String id) {
        return mapper.getProjectsOfUser(id);
    }

}
