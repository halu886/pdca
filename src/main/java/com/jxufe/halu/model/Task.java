package com.jxufe.halu.model;

import java.sql.Timestamp;

public class Task implements IBaseBean {
    private String taskId;
    private String taskName;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String taskType;
    private String description;
    private String pTaskId;
    private String projectId;
    private String progress;

    private String nodeProgress;
    private Integer tno;
    private Timestamp startDate;
    private Timestamp endDate;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public  Task(){
        super();
    }

    public Task(String taskId) {
        super();
        this.taskId = taskId;
    }

    public Task(String taskId, String taskName, Timestamp createDate, Timestamp updateDate,
                String taskType, String description, String ptaskId, String projectId, Integer tno) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.taskType = taskType;
        this.description = description;
        this.pTaskId = ptaskId;
        this.projectId = projectId;
        this.tno = tno;
    }

    public Integer getTno() {
        return tno;
    }

    public void setTno(Integer tno) {
        this.tno = tno;
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

    public String getPTaskId() {
        return pTaskId;
    }

    public void setPTaskId(String ptaskId) {
        this.pTaskId= ptaskId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return this.taskName;
    }

    public String getId() {
        return this.taskId;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public  String[] getTags(){
        return new String[]{this.getProgress()};
    }

    public String getNodeProgress() {
        return nodeProgress;
    }
    public void setNodeProgress(String nodeProgress) {
        this.nodeProgress = nodeProgress;
    }
}
