package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.util.Tree;

import java.util.List;

public interface ITaskService {
    public Task findTaskById(String id);
    public void addTask(Task task);
    public List<Task> getAllTasks();
    List<Tree<Task>> getTaskTreeByProjectId(String projectId);
}
