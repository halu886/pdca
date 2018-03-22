package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private IUserService userService = new UserServiceImpl();

////    @RequestMapping("/test")
////    public String index() {
////        return "/index.jsp";
////    }


    @RequestMapping("/login")
    public ModelAndView Login(@ModelAttribute User user, ModelMap map, HttpServletRequest request) {
        User userData = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            userData = userService.findUserByName(user.getUsername());
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userData.getUserID(), userData.getPassword());
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:.");
        }
        subject.getSession().setAttribute("user", userData);
        return new ModelAndView("main");
    }
}
