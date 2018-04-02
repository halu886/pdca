package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface TaskMapper {
    @Select("select * from task where TaskID = #{id}")
    Task findTaskById(String id);

    @Insert("Insert into task(taskId,taskName,createDate,updateDate,taskType,Description,pTaskId,projectId,tno)" +
            " values(#{taskId},#{taskName},#{createDate},#{updateDate},#{taskType},#{description},#{pTaskId},#{projectId },#{tno})")
    void addTask(Task task);

    @Select("select * from task")
    List<Task> getAllTasks();

    @Select("SELECT * FROM task WHERE ProjectID = #{projectId}")
    List<Task> getTaskByProjectId(String projectId);

    @Update("UPDATE task SET TaskName =#{taskName},TaskType=#{taskType},Description=#{description},Tno=#{tno} ,Progress=#{progress},NodeProgress=#{nodeProgress} WHERE TaskID=#{taskId}")
    int update(Task task);

    @InsertProvider(type = TaskMapperProvider.class, method = "insertBatch")
    int insertBatch(List<Task> taskList);

    @Select("SELECT\n" +
            "\tcount(*)\n" +
            "FROM\n" +
            "\ttask\n" +
            "WHERE\n" +
            "task.PTaskID = #{id}")
    int countChildById(String id);

    @SelectProvider(type = TaskMapperProvider.class,method = "queryByTask")
    List<Task> queryByTask(Task queryTask);


    //按周查询新建任务
    @Select("SELECT\n" +
            "\tcount(*) as value,\n" +
            "\tWEEK as name\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tDAYNAME(CreateDate) AS WEEK\n" +
            "\t\tFROM\n" +
            "\t\t\ttask\n" +
            "\t\tLEFT JOIN mid_user_project AS mup ON task.ProjectID = mup.ProjectID\n" +
            "\t\tLEFT JOIN `user` ON `user`.UserID = mup.UserID\n" +
            "\t\tWHERE\n" +
            "\t\t\t`user`.UserID = #{userID}\n" +
            "\t) AS alias_task\n" +
            "GROUP BY\n" +
            "\tWEEK")
    List<Map> countTaskByUserID(String userID);

    @Select("SELECT\n" +
            "\tTaskType AS name,\n" +
            "\tcount(*) AS\n" +
            "value\n" +
            "\n" +
            "FROM\n" +
            "\t`user`\n" +
            "INNER JOIN mid_user_project ON `user`.UserID = mid_user_project.UserID\n" +
            "INNER JOIN task ON mid_user_project.ProjectID = task.ProjectID\n" +
            "WHERE\n" +
            "\tUSER .UserID =#{userID}\n" +
            "GROUP BY\n" +
            "\tTaskType")
    List<Map> countType(String userID);

    @Select("SELECT\n" +
            "\tSUM(a.NodeProgress) as value,\n" +
            "\n" +
            "IF (\n" +
            "\ta.`handler` = 1,\n" +
            "\t'handler',\n" +
            "\t'unHandler'\n" +
            ") AS type\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\n" +
            "\t\tIF (Progress = 100, 1, 0) AS 'handler',\n" +
            "\t\tNodeProgress\n" +
            "\tFROM\n" +
            "\t\t`user`\n" +
            "\tINNER JOIN mid_user_project ON `user`.UserID = mid_user_project.UserID\n" +
            "\tINNER JOIN task ON mid_user_project.ProjectID = task.ProjectID\n" +
            "\tWHERE\n" +
            "\t\tUSER .UserID = #{userID}\n" +
            "\t) AS a\n" +
            "GROUP BY\n" +
            "\ta.`handler`")
    List<Map> countProgressByUserId(String userID);

    @Select("SELECT\n" +
            "\tweekcount.type,\n" +
            "\tweekcount.weekday,\n" +
            "\tCOUNT(*) AS value\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tTaskType AS type,\n" +
            "\t\t\tDAYNAME(UpdateDate) AS weekday\n" +
            "\t\tFROM\n" +
            "\t\t\t`user`\n" +
            "\t\tINNER JOIN mid_user_project ON `user`.UserID = mid_user_project.UserID\n" +
            "\t\tINNER JOIN task ON mid_user_project.ProjectID = task.ProjectID\n" +
            "\t\tWHERE\n" +
            "\t\t\t`user`.UserID = #{userID}\n" +
            "\t) AS weekcount\n" +
            "GROUP BY\n" +
            "\tweekcount.type,\n" +
            "\tweekcount.weekday")
    List<Map> updateWeekday(String userID);
}
