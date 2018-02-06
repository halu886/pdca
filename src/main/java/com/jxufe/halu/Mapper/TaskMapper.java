package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TaskMapper {
    @Select("select * from task where TaskID = #{id}")
    Task findTaskById(String id);

    @Insert("Insert into task(taskId,taskName,createDate,updateDate,taskType,Description,pTaskId,projectId)" +
            " values(#{taskId},#{taskId},#{taskName},#{createDate},#{updateDate},#{taskType},#{Description},#{pTaskId},#{projectId })")
    void addTask(Task task);

    @Select("select * from task")
    List<Task> getAllTasks();

    @Select("SELECT * FROM task WHERE ProjectID = #{projectId}")
    List<Task> getTaskByProjectId(String projectId);

    @Update("UPDATE task SET TaskName =#{taskName},TaskType=#{taskType},Description=#{description},Tno=#{tno} WHERE TaskID=#{taskId}")
    int update(Task task);
}
