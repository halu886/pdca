package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.Task;

import java.util.List;

public interface ITaskDao {
    public Task findTaskById(String id);
    public void addTask(Task task);
    public List<Task> getAllTasks();
    public int update(Task task);
    List<Task> findTaskByProjectId(String projectId);
}
