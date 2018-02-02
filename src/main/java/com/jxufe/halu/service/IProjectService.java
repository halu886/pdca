package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;

import java.util.List;

public interface IProjectService {
     public Project findProjectById(String id);
     public void addProject(Project project,User user);
     public List<Project> getAllProjects();
     public int update(Project project);
     public List<Project> getProjectsOfUser(String id);
}
