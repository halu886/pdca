package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IProjectDao;
import com.jxufe.halu.Dao.ProjectDaoImpl;
import com.jxufe.halu.model.Project;

import java.util.List;

public class ProjectServiceImpl implements  IProjectService {

    private IProjectDao projectDao;

    public ProjectServiceImpl() {
        projectDao = new ProjectDaoImpl();
    }

    public Project findProjectById(String id) {
        return  projectDao.findProjectById(id);
    }

    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public int update(Project project){
        return  projectDao.update(project);
    }
}
