package com.jxufe.halu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class Project {
    private String projectID;
    private String name;
    private Timestamp CreateDate;

    public Project(){
        super();
    }

    public Project(String projectID) {
        super();
        this.projectID = projectID;
    }

    public Project(String projectID, String name, Timestamp createDate) {
        super();
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

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp createDate) {
        CreateDate = createDate;
    }
}
