package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"userID","userName"})
public class LoginController {

    private IUserService userService=  new UserServiceImpl();

    @RequestMapping("/login")
    public ModelAndView Login(@ModelAttribute User user, ModelMap map, HttpServletRequest request){
        User userData = null;
        try{
            userData = userService.findUserById(user.getUserID());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(userData instanceof  User && userData.getPassword().equals(user.getPassword())){
                map.put("userID",userData.getUserID());
                map.put("userName",userData.getUsername());
                return new ModelAndView("main");
            } else {
                return new ModelAndView(new RedirectView("/"));
            }
        }
    }
}
