package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;

import java.util.List;

public interface IProjectService {
     public Project findProjectById(String id);
     public void addProject(Project project);
     public List<Project> getAllProjects();
     public int update(Project project);
}
