package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class LoginController {

    private IUserService userService = new UserServiceImpl();


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    Map Login(User user, ModelMap map, HttpServletRequest request) {
        User userData = null;
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> result = new HashMap<>();
        result.put("status",false);
        result.put("message","登录失败");
        try {
            userData = userService.findUserByName(user.getUsername());
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userData.getUserID(), userData.getPassword());
            subject.login(usernamePasswordToken);
            Set role =  userService.queryRoleByID(userData.getUserID());
            result.put("role",role.toArray());
            result.put("status",true);
            result.put("message","登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message","登录失败");
        }
        subject.getSession().setAttribute("user", userData);
        return result;
    }
}
