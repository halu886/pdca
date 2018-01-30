package com.jxufe.halu.service;

import com.jxufe.halu.util.Tree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/task")
public class TaskController {

    private ITaskService  service=new TaskServiceImpl();

//    @RequestMapping("/tree")
//    public @ResponseBody
//    Tree getTaskTreeBy
}
