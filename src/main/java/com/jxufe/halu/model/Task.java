package com.jxufe.halu.model;

import java.sql.Timestamp;

public class Task {
    private String taskId;
    private String taskName;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String taskType;
    private String description;
    private String ptaskId;
    private String projectId;

    public Task(String taskId) {
        this.taskId = taskId;
    }

    public Task(String taskId, String taskName, Timestamp createDate, Timestamp updateDate, String taskType, String description, String ptaskId, String projectId) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.taskType = taskType;
        this.description = description;
        this.ptaskId = ptaskId;
        this.projectId = projectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPtaskId() {
        return ptaskId;
    }

    public void setPtaskId(String ptaskId) {
        this.ptaskId = ptaskId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
