package com.jxufe.halu.controller;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.service.IProjectService;
import com.jxufe.halu.service.ProjectServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private IProjectService service = new ProjectServiceImpl();

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectsList(Model model){
        return  service.getAllProjects();
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        Project project = service.findProjectById("1");
        return  new ModelAndView("/WEB-INF/index.jsp","projectName",project.getName());
    }
}
