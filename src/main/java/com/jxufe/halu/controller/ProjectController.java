package com.jxufe.halu.controller;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.service.IProjectService;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.ProjectServiceImpl;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private IProjectService projectService = new ProjectServiceImpl();
    private IUserService userService = new UserServiceImpl();

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(HttpSession session){
        String userID = (String)session.getAttribute("userID");
        List<Project> project = userService.getProjectsOfUser(userID);
        return  new ModelAndView("project","projects",project);
    }
}
