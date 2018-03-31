package com.jxufe.halu.Dao;

import com.jxufe.halu.Mapper.TaskMapper;
import com.jxufe.halu.model.Task;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

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
}
