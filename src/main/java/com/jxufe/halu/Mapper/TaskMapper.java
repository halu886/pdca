package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskMapper {
    @Select("select * from task where TaskID = #{id}")
    Task findTaskById(String id);

    @Insert("Insert into task(taskId,taskName,createDate,updateDate,taskType,Description,pTaskId,projectId)" +
            " values(#{taskId},#{taskId},#{taskName},#{createDate},#{updateDate},#{taskType},#{Description},#{pTaskId},#{projectId })")
    void addTask(Task task);

    @Select("select * from task")
    List<Task> getAllTasks();
}
