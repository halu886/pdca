package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProjectMapper {

    @Select("select * from project where projectid = #{id}")
    Project findProjectById(String id);

    @Insert("insert into project(name,createdate) value(#{name},#{createDate})")
    void addProject(Project project);

    @Update("UPDATE project " +
            "SET `Name` = #{name}," +
            "CreateDate =#{createDate} " +
            "WHERE " +
            "ProjectID = #{projectID}")
    int update(Project project);

    @Select("select * from project")
    List<Project> getAllProject();
}
