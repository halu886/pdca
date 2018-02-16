package com.jxufe.halu.controller;

import com.alibaba.fastjson.JSONArray;
import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.Task;
import com.jxufe.halu.service.ITaskService;
import com.jxufe.halu.service.TaskServiceImpl;
import com.jxufe.halu.util.Tree;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
@SessionAttributes({"projectID"})
public class TaskController {

    private ITaskService service = new TaskServiceImpl();

    @RequestMapping("/index")
    public ModelAndView toIndex(
            @RequestParam("projectID") String projeceId,
            ModelMap map) {
        map.put("projectID", projeceId);
        return new ModelAndView("task");
    }


    @RequestMapping("/tree")
    public @ResponseBody
    Object getTaskTreeBy(HttpSession session) {
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("data", null);
        rs.put("status", false);
        rs.put("message", "获取失败");
        String projectId = null;
        try {
            projectId = (String) session.getAttribute("projectID");
            List<Tree<Task>> taskTree = service.getTaskTreeByProjectId(projectId);
            JSONArray taskJsonArray = new JSONArray();
            for (Tree task : taskTree) {
                taskJsonArray.add(task.toJson());
            }
            rs.put("status", true);
            rs.put("data", taskJsonArray);
            rs.put("message", "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map update(Task task) {
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("status", false);
//        rs.put("data",null);
        rs.put("message", "更新失败");
        try {
            service.updateTask(task);
            rs.put("status", true);
            rs.put("message", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rs;
        }
    }

    @RequestMapping("/add/:type")
    public @ResponseBody
    Map addTask(@RequestBody Map body, @Param("type") String type,HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", false);
        result.put("message", "添加失败");
        try {
            Project  project = (Project)session.getAttribute("project");
            String childNameKey = "taskName";
            String childDescription = "description";
            String childPTaskId = "pTaskId";
            Task typeTask = new Task();
            int increateRs = 0;
            switch (type) {
                case "t":
                    String nameKey = "TypeTaskName";
                    String descriptionKey = "TypeTaskDescription";
                    String TypeTaskPrograss = "TypeTaskPrograss";
                    String[] typeList = {"p", "d", "c", "a"};
                    String pTaskId = (String) body.get("pTaskId");
                    List<Task> taskList =new ArrayList<Task>();
                    for (int i = 0; i < 4; i++) {
                        Task task = new Task();
                        String name = typeList[i]+nameKey;
                        String description =  typeList[i]+descriptionKey;
                        String progress = typeList[i]+TypeTaskPrograss;
                        task.setTaskName((String) body.get(name));
                        task.setDescription((String)body.get(description));
                        task.setTaskType(typeList[i]);
                        task.setProjectId(pTaskId);
                        taskList.add(task);
                    }
                    increateRs =  service.increateTypeTask(taskList);
                    if(increateRs != 4){
                        throw  new Exception("添加确实");
                    }
                    break;
                case "p":
                    typeTask.setTaskType("t");
                    typeTask.setDescription((body.get(childDescription) == null)?"":(String) body.get(childNameKey));
                    typeTask.setTaskName((String) body.get(childNameKey));
                    typeTask.setProjectId(project.getProjectID());
                    typeTask.setPtaskId((String)body.get(childPTaskId));
                    increateRs= service.increateStepTask(typeTask);
                    if(increateRs != 1){
                        throw new Exception("增加异常");
                    }
                    break;
                case "d":
                    Task task = new Task();
                    task.setTaskType("t");
                    task.setDescription((body.get(childDescription) == null)?"":(String) body.get(childNameKey));
                    task.setTaskName((String) body.get(childNameKey));
                    task.setProjectId(project.getProjectID());
                    task.setPtaskId((String)body.get(childPTaskId));
                    increateRs= service.increateStepTask(task);
                    if(increateRs != 1){
                        throw new Exception("增加异常");
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", "添加异常");
        } finally {
            return result;
        }

    }
}
