package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IProjectDao;
import com.jxufe.halu.Dao.ProjectDaoImpl;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public class ProjectServiceImpl implements  IProjectService {

    private IProjectDao projectDao;

    public ProjectServiceImpl() {
        projectDao = new ProjectDaoImpl();
    }

    public Project findProjectById(String id) {
        return  projectDao.findProjectById(id);
    }

    public void addProject(Project project,User user) {
        projectDao.addProject(project);
        projectDao.addAssoiateUser(user,project);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public int update(Project project){
        return  projectDao.update(project);
    }
    public List<Project> getProjectsOfUser(String id){
        return projectDao.getProjectOfUser(id);
    }
}
