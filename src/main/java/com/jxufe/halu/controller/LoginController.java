package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IProjectService;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes({"useID","userName"})
public class LoginController {

    private IUserService userService=  new UserServiceImpl();

    @RequestMapping("/login")
    public ModelAndView Login(@ModelAttribute User user, ModelMap map){
        User userData = null;
        try{
            userData = userService.findUserById(user.getUserID());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(userData instanceof  User && userData.getPassword().equals(user.getPassword())){
                map.put("userID",user.getUserID());
                map.put("userName",user.getPassword());

                return new ModelAndView("index");
            } else {
                return new ModelAndView(new RedirectView("/"));
            }
        }
    }
}
