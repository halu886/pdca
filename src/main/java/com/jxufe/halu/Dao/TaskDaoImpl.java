package com.jxufe.halu.Dao;

import com.jxufe.halu.Mapper.TaskMapper;
import com.jxufe.halu.model.Task;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class TaskDaoImpl implements ITaskDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    private TaskMapper mapper;

    public TaskDaoImpl() {
        String resource = "config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sessionFactory.openSession();
            mapper = session.getMapper(TaskMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task findTaskById(String id) {
        return mapper.findTaskById(id);
    }

    public void addTask(Task task) {
        mapper.addTask(task);
        session.commit();
    }

    public List<Task> getAllTasks() {
        List<Task> list = mapper.getAllTasks();
        return list;
    }

    public int update(Task task) {
        int row = mapper.update(task);
        session.commit();
        return row;
    }

    public List<Task> findTaskByProjectId(String projectId) {
        return mapper.getTaskByProjectId(projectId);
    }

    @Override
    public int insertBatch(List<Task> taskList) {
        int rs = mapper.insertBatch(taskList);
        session.commit();
        return rs;
    }

    @Override
    public int countChildById(String id) {
        return mapper.countChildById(id);
    }

    @Override
    public List<Task> queryByTask(Task queryTask) {
        return mapper.queryByTask(queryTask);
    }

    @Override
    public List<Map> countTaskByUserID(String userID, String week) {
        return mapper.countTaskByUserID(userID);
    }

    @Override
    public List<Map> countType(String userID) {
        List<Map> datas = mapper.countType(userID);
        return datas;
    }

    @Override
    public Map countProgressByUserId(String userID) {
        List<Map> datas = mapper.countProgressByUserId(userID);
        Map result = new HashMap();
        for (Map data : datas) {
            result.put(data.get("type"), data.get("value"));
        }
        return result;
    }

    @Override
    public Map updateWeekday(String userID) {
        List<Map> data = mapper.updateWeekday(userID);
        List<String> weekTypes = Arrays.asList(new String[]{"morning","tuesday","wednesday","thursday","friday","saturday","sunday"});
        List<String> taskTypes = Arrays.asList(new String[]{"T","P","D","C","A"});
        Map resultData = new HashMap();
        for (String taskType:taskTypes){
            List<Integer> weekData = Arrays.asList(new Integer[]{0,0,0,0,0,0,0});
            resultData.put(taskType,weekData);
        }
        Iterator dataIterator = data.iterator();
        while (dataIterator.hasNext()){
            Map item = (Map) dataIterator.next();
            String itemType = (String) item.get("type");
            String weekType = (String) item.get("weekday");
            List weekList = (List)resultData.get(itemType);
            weekList.set( weekTypes.indexOf(weekType.toLowerCase()),((Long)item.get("value")).intValue());
        }
        return resultData;
    }

    @Override
    public void deleteByProjectId(String id) {
        mapper.deleteByProjectId(id);
    }

    @Override
    public int countRootTaskByProjectId(String projectId) {
        return mapper.countRootTaskByProjectId(projectId);
    }

    @Override
    public int deleteByIds(List taskIds) {
        return mapper.deleteByIds(taskIds);
    }

    @Override
    public void deleteById(Object d) {
         mapper.deleteById(d);
    }
}
