package com.jxufe.halu.controller;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IProjectService;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.ProjectServiceImpl;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private IProjectService projectService = new ProjectServiceImpl();

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        List<Project> project = projectService.getProjectsOfUser(userID);
        return new ModelAndView("project", "projects", project);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public @ResponseBody
    Project getProjectById(@RequestParam("projectId") String projectId, HttpServletRequest request) {
        Project project = projectService.findProjectById(projectId);
        return project;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateProjectById(Model model, Project projectParam) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "false");
        result.put("message", "更新异常");
        try {
            int numUpdate = projectService.update(projectParam);
            if (numUpdate == 1) {
                result.put("status", "status");
                result.put("message", "更新成功");
            } else {
                throw new Exception("更新异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> add(Model model, Project paramProject,HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "false");
        result.put("message", "添加失败");
        Project addProject = new Project(null, paramProject.getName(), paramProject.getCreateDate());
        try {
            projectService.addProject(addProject,new User((String) session.getAttribute("userID"),null,null));
            result.put("status", "true");
            result.put("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
