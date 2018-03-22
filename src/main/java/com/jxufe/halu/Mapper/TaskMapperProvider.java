package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Task;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class TaskMapperProvider {

    public String insertBatch(Map map){
        List<Task> taskList = (List<Task>)map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO task");
        sb.append("(TaskName,CreateDate,UpdateDate,TaskType,Description,PTaskID,ProjectID,Tno)");
        sb.append("VAlUES");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].taskName}," +
                "#'{'list[{0}].createDate}," +
                "#'{'list[{0}].updateDate}," +
                "#'{'list[{0}].taskType}," +
                "#'{'list[{0}].description}," +
                "#'{'list[{0}].pTaskId}," +
                "#'{'list[{0}].projectId}," +
                "#'{'list[{0}].tno}" +
                ")");
        for (int i=0;i<taskList.size();i++){
            sb.append(mf.format(new Object[]{i}));
            if(i<taskList.size() -1){
                sb.append(",");
            }
        }
        return  sb.toString();
    }

    public String queryByTask(Map map){
        Task queryTask = (Task) map.get("queryTask");
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM WHERE");
        String[] paramList = {"taskId","taskName","taskType","description","pTaskId","project","progress","tno"};

        return  null;
    }
}
