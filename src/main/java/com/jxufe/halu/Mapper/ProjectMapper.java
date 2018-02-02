package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProjectMapper {

    @Select("select * from project where projectid = #{id}")
    Project findProjectById(String id);

    @Options(
            useGeneratedKeys = true,
            keyProperty = "projectID"
    )
    @Insert("insert into project(name,createdate) value(#{name},#{createDate})")
    void addProject(Project project);


    @Insert("insert into mid_user_project(ProjectID,UserID) VALUE(#{project.projectID},#{user.userID})")
    void addAssoiateUser(@Param("project") Project project, @Param("user") User user);

    @Update("UPDATE project " +
            "SET `Name` = #{name}," +
            "CreateDate =#{createDate} " +
            "WHERE " +
            "ProjectID = #{projectID}")
    int update(Project project);

    @Select("select * from project")
    List<Project> getAllProject();

    @Select("SELECT\n" +
            "\tproject.ProjectID,\n" +
            "\t`Name`,\n" +
            "\tCreateDate \n" +
            "FROM\n" +
            "\tproject\n" +
            "\tINNER JOIN mid_user_project m ON project.ProjectID = m.ProjectID\n" +
            "\tINNER JOIN `user` ON `user`.UserID = m.UserID \n" +
            "WHERE\n" +
            "\tUSER.UserID = #{id}")
    List<Project> getProjectsOfUser(String id);
}
