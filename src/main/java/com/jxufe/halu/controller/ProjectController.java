package com.jxufe.halu.controller;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IProjectService;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.ProjectServiceImpl;
import com.jxufe.halu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
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
    public @ResponseBody  Map index() {
        Map result = new HashMap();
        result.put("status",false);
        result.put("message","查询失败");
        try{
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            List<Project> project = projectService.getProjectsOfUser(user.getUserID());
            result.put("data",project);
            result.put("status",true);
            result.put("message","查询成功");
        } catch (Exception e){
            result.put("message","查询异常");
            e.printStackTrace();
        }
        return result;
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
    Map<String, Object> add(@RequestBody Project project) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "false");
        result.put("message", "添加失败");
        try {
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            Project addProject = new Project(null,project.getName(), new Timestamp(System.currentTimeMillis()));
            projectService.addProject(addProject,user);
            result.put("status", "true");
            result.put("message", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/table")
    public @ResponseBody
    Map<String, Object> queryTable(HttpServletRequest request) {
        Map result = new HashMap();
        result.put("status", false);
        result.put("message", "查询失败");
        try {
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            String searchParam = request.getParameter("searchParam");
            if (page < 0 || size < 0) {
                throw new Exception("参数异常");
            }
            List<Map> data = projectService.queryTable(page,size,searchParam,user.getUserID());
            int total = projectService.countProject(searchParam,user.getUserID());
            result.put("data",data);
            result.put("status",true);
            result.put("message","查询正常");
            result.put("total",total);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message","查询异常");
        }
        return  result;
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    public @ResponseBody Map<String,Object> deleteById(HttpServletRequest req){
        Map result = new HashMap();
        result.put("status",false);
        result.put("message","删除失败");
        try {
            String id = req.getParameter("id");
            if(!(id instanceof  String) )throw new Exception("Id不存在");
           int num =  projectService.deleteById(id);
           if(num!=0){
               result.put("status",true);
               result.put("message","删除成功");
               result.put("num",num);
           }
        }catch (Exception e){
            e.printStackTrace();
            result.put("message","删除异常");
        }
        return  result;
    }
}
