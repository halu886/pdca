package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public interface IProjectDao {
    public Project findProjectById(String id);
    public  void addProject(Project project);
    public List<Project>  getAllProjects();
}
