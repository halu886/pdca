package com.jxufe.halu.service;

import com.jxufe.halu.Dao.ITaskDao;
import com.jxufe.halu.Dao.TaskDaoImpl;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.util.DateUtil;
import com.jxufe.halu.util.Tree;

import java.util.*;

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
            if (((Task) pNode.getT()).getPTaskId() == null || ((Task) pNode.getT()).getPTaskId().equals("")) {
                rootTaskList.add(pNode);
            }
            for (Tree node : taskList) {
                if (((Task) node.getT()).getPTaskId() != null && ((Task) node.getT()).getPTaskId().equals(((Task) pNode.getT()).getTaskId())) {
                    pNode.addChild(node);
                    node.setParentT(pNode.getT());
                }
            }
        }
        return rootTaskList;
    }

    public int updateTask(Task task) {
        task.setUpdateDate(DateUtil.getCurrentTimestamp());
        return taskDao.update(task);
    }


    @Override
    public int increateTypeTask(List<Task> taskList) throws Exception {
        if (taskList.size() != 4) {
            throw new Exception("参数异常");
        }
        return taskDao.insertBatch(taskList);
    }

    @Override
    public void increateStepTask(Task typeTask) {
        taskDao.addTask(typeTask);
    }

    @Override
    public boolean isHasChild(String taskId) {
        int num = taskDao.countChildById(taskId);
        if (num == 0) {
            return false;
        }
        return true;
    }

    @Override
    public int numChild(String taskId) {
        int num = taskDao.countChildById(taskId);
        return num;
    }

    @Override
    public boolean isValidOver(String taskId) throws Exception {
        Task task = this.findTaskById(taskId);
        List<String> types = Arrays.asList(new String[]{"P", "D", "C", "A"});
        String type = task.getTaskType();
        switch (type) {
            case "T": {
                Task queryTask = new Task();
                queryTask.setTaskType("A");
                queryTask.setPTaskId(task.getTaskId());
                List<Task> tasks = this.queryByTask(queryTask);
                Task taskTypeA = tasks.get(0);
                if (!taskTypeA.getProgress().equals("100")) {
                    return false;
                }
                break;
            }
            case "P":
            case "D":
            case "C":
            case "A": {
                int typeIndex = types.indexOf(type);
                if (typeIndex != 0) {
                    String preType = types.get(--typeIndex);
                    Task queryTask = new Task();
                    queryTask.setPTaskId(task.getPTaskId());
                    queryTask.setTaskType(preType);
                    List<Task> preTask = this.queryByTask(queryTask);
                    if (!preTask.get(0).getProgress().equals("100")) {
                        return false;
                    }
                }
                Task queryTask = new Task();
                queryTask.setPTaskId(task.getTaskId());
                List<Task> taskChilds = this.queryByTask(queryTask);
                for (Task taskChild : taskChilds) {
                    if (!taskChild.getProgress().equals("100")) {
                        return false;
                    }
                }
                break;
            }
            default:
                throw new Exception("数据异常:type类型不存在\t" + type);
        }
        return true;
    }

    /**
     * 完结任务
     *
     * @param taskId 任务ID
     */
    @Override
    public void overTask(String taskId) throws Exception {
        Task task = this.findTaskById(taskId);
        task.setProgress("100");
        this.updateTask(task);
        if (!(task.getPTaskId() instanceof String)) {
            return;
        }
        Task taskParent = this.findTaskById(task.getPTaskId());
        int progressParent = Integer.valueOf(taskParent.getProgress());
        if ((progressParent + Integer.valueOf(task.getNodeProgress()) == 100) && this.isValidOver(taskParent.getTaskId())) {
            this.overTask(taskParent.getTaskId());
        } else {
            taskParent.setProgress(progressParent + Integer.valueOf(task.getNodeProgress()) + "");
            this.updateTask(taskParent);
        }

    }

    @Override
    public List<Task> queryByTask(Task queryTask) {
        return this.taskDao.queryByTask(queryTask);
    }

    @Override
    public List<Map> countTaskByUserID(String userID, String dataType) {
        return taskDao.countTaskByUserID(userID, "week");
    }

    @Override
    public List<Map> countType(String userID) {
        return taskDao.countType(userID);
    }

    @Override
    public Map countProgressByUserId(String userID) {
        return null;
    }
}
