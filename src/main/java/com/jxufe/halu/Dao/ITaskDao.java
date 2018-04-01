package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Task;

import java.util.List;
import java.util.Map;

public interface ITaskDao {
    public Task findTaskById(String id);
    public void addTask(Task task);
    public List<Task> getAllTasks();
    public int update(Task task);
    List<Task> findTaskByProjectId(String projectId);
    public int insertBatch(List<Task> taskList);
    public int countChildById(String id);
    List<Task> queryByTask(Task queryTask);

    List<Map> countTaskByUserID(String userID, String week);

    List<Map> countType(String userID);
}
