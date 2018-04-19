package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IProjectDao;
import com.jxufe.halu.Dao.ProjectDaoImpl;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;
import java.util.Map;

public class ProjectServiceImpl implements  IProjectService {

    private IProjectDao projectDao;
    private  ITaskService taskService = new TaskServiceImpl();

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

    @Override
    public List<Map> queryTable(int page, int size, String searchParam,String userID) {
        return projectDao.queryTable(page,size,searchParam,userID);
    }

    @Override
    public int countProject(String searchParam, String userID) {
        return projectDao.countProject(searchParam,userID);
    }

    @Override
    public int deleteById(String id) {
        int  num=projectDao.deleteById(id);
        if(num!=0){
            taskService.deleteByProjectId(id);
        }
        return num;
    }
}
