package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectMapper {

    @Select("select * from project where projectid = #{id}")
    Project findProjectById(String id);

    @Insert("insert into user(name,createdate) value(#{name},#{password})")
    void addProject(Project project);

    @Select("select * from project")
    List<Project> getAllProject();
}
