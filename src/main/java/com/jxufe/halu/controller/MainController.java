package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private IUserService service = new UserServiceImpl();

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(User user){
        return "index";
    }

    @RequestMapping(value = "/nice",method = RequestMethod.GET)
    @ResponseBody
    public List<User> nice(Model model){
        return  service.getAllUsers();
    }

    @RequestMapping(value ="/toJson",method = RequestMethod.GET)
    @ResponseBody
    public User toJson(@ModelAttribute("user") User user){
        service.addUser(user);
        return service.findUserById("2");
    }

}
