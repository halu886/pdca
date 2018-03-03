package com.jxufe.halu.service;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.util.Tree;

import java.util.List;
import java.util.Map;

public interface ITaskService {
    public Task findTaskById(String id);
    public void addTask(Task task);
    public List<Task> getAllTasks();
    public List<Tree<Task>> getTaskTreeByProjectId(String projectId);
    public int updateTask(Task task);
    public int increateTypeTask(List<Task> taskList) throws Exception;
    public int increateStepTask(Task typeTask);
}
