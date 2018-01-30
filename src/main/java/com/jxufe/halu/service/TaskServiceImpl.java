package com.jxufe.halu.service;

import com.jxufe.halu.Dao.ITaskDao;
import com.jxufe.halu.Dao.TaskDaoImpl;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.service.ITaskService;

import java.util.List;

public class TaskServiceImpl implements ITaskService {

    private ITaskDao taskDao;

    public TaskServiceImpl(){
        taskDao = new TaskDaoImpl();
    }

    public Task findTaskById(String id) {
        return taskDao .findTaskById(id);
    }

    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }
}
