package com.jxufe.halu.service;

import com.jxufe.halu.Dao.IProjectDao;
import com.jxufe.halu.Dao.ITaskDao;
import com.jxufe.halu.Dao.ProjectDaoImpl;
import com.jxufe.halu.Dao.TaskDaoImpl;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.service.ITaskService;
import com.jxufe.halu.util.Tree;

import javax.print.DocFlavor;
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
        List<Tree<Task>> taskList = new ArrayList<Tree<Task>>();
        for (Task task : tasks) {
            taskList.add(new Tree<Task>(task));
        }
        for (Tree pNode : taskList) {
            if (((Task) pNode.getT()).getPtaskId() == null || ((Task) pNode.getT()).getPtaskId().equals("")) {
                rootTaskList.add(pNode);
            }
            for (Tree node : taskList) {
                if (((Task) node.getT()).getPtaskId() != null && ((Task) node.getT()).getPtaskId().equals(((Task) pNode.getT()).getTaskId())) {
                    pNode.addChild(node);
                    node.setParentT(pNode.getT());
                }
            }
        }
        return rootTaskList;
    }

    public int updateTask(Task task) {
        Task taskFromData = taskDao.findTaskById(task.getTaskId());
//        taskFromData.setCreateDate(tak);
        taskFromData.setDescription(task.getDescription() instanceof String?task.getDescription():taskFromData.getDescription());
        taskFromData.setTaskName(task.getTaskName() instanceof String?task.getTaskName():taskFromData.getTaskName());
        if(taskFromData.getTaskType().equals("T")){
            taskFromData.setTno(task.getTno()!=null?task.getTno():taskFromData.getTno());
        }
        taskFromData.setTaskType(task.getTaskType() instanceof String?task.getTaskType():taskFromData.getTaskType());
        return taskDao.update(taskFromData);
    }


//    public List<>
}
