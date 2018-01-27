package com.jxufe.halu.service;

import com.jxufe.halu.model.Task;

import java.util.List;

public interface ITaskService {
    public Task findTaskById(String id);
    public void addTask(Task task);
    public List<Task> getAllTasks();
}
