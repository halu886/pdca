package com.jxufe.halu.model;

import java.sql.Timestamp;

public class Project {
    private String projectID;
    private String name;
    private Timestamp CreateDate;

    public Project(String projectID) {
        this.projectID = projectID;
    }

    public Project(String projectID, String name, Timestamp createDate) {
        this.projectID = projectID;
        this.name = name;
        CreateDate = createDate;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }
}
