package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Task;

import java.lang.reflect.Field;
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

    public String queryByTask(Task queryTask) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM Task WHERE ");
        String[] paramList = {"taskId","taskName","taskType","description","pTaskId","projectId","progress","tno"};
        Class taskCalzz = queryTask.getClass();
        int countAppend = 0 ;
        for(String param:paramList){
            Field paramField=  taskCalzz.getDeclaredField(param);
            paramField.setAccessible(true);
            String taskFieldValue = (String)paramField.get(queryTask);
            if(taskFieldValue instanceof  String &&!taskFieldValue.equals("")){
                sb.append(param +" = #{"+param+"} and ");
                ++countAppend;
            }
        }
        if(countAppend==0) throw  new Exception("参数异常");
        sb.delete(sb.length()-4,sb.length()-1);
        return  sb.toString();
    }
}
