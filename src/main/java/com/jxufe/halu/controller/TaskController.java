package com.jxufe.halu.controller;

import com.jxufe.halu.model.Task;
import com.jxufe.halu.service.ITaskService;
import com.jxufe.halu.service.TaskServiceImpl;
import com.jxufe.halu.util.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
@SessionAttributes({"projectID"})
public class TaskController {

    private ITaskService service=new TaskServiceImpl();

    @RequestMapping("/index")
    public ModelAndView toIndex(
            @RequestParam("projectID")String projeceId,
            ModelMap map){
        map.put("projectID",projeceId);
        return  new ModelAndView("task");
    }


    @RequestMapping("/tree")
    public @ResponseBody Object getTaskTreeBy(HttpSession session){
        String projectId = (String)session.getAttribute("projectID");
        List<Tree<Task>> taskTree =  service.getTaskTreeByProjectId(projectId);
        return taskTree;
    }
}
