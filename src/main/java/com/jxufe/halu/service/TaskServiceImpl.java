package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IProjectDao;
import com.jxufe.halu.Dao.ITaskDao;
import com.jxufe.halu.Dao.ProjectDaoImpl;
import com.jxufe.halu.Dao.TaskDaoImpl;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.service.ITaskService;
import com.jxufe.halu.util.Tree;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements ITaskService {

    private ITaskDao taskDao;

    public TaskServiceImpl() {
        taskDao = new TaskDaoImpl();
    }

    public Task findTaskById(String id) {
        return taskDao.findTaskById(id);
    }

    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public List<Tree<Task>> getTaskTreeByProjectId(String projectId) {
        List<Task> tasks = taskDao.findTaskByProjectId(projectId);
        List<Tree<Task>> rootTaskList = new ArrayList<Tree<Task>>();
        for (Task task : tasks) {
            if (task.getPtaskId() == null || task.getPtaskId().equals("")) {
                Tree<Task> tree = new Tree<Task>(task);
                for (Task taskChild : tasks) {
                    if (taskChild.getPtaskId()!=null&&taskChild.getPtaskId().equals(task.getTaskId())) {
                        tree.addChild(new Tree<Task>(task));
                    }
                }
                rootTaskList.add(tree);
            }
        }
        return rootTaskList;
    }

//    public List<>
}
