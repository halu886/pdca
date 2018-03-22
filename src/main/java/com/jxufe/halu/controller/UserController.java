package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    IUserService service = new UserServiceImpl();

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody
    Map<String,Object> toRegister(@RequestBody User user){
        Map<String,Object> result = new HashMap<>();
        result.put("status",false);
        result.put("message","查询失败");
        try{
            User  addUser = new User();
            addUser.setUsername(user.getUsername());
            addUser.setPassword(user.getPassword());
            if(addUser.getUsername().trim().equals("")||addUser.getPassword().trim().equals("")){
                throw  new Exception("用户参数异常");
            }
            service.register(addUser,"normalUser");
            result.put("status",true);
            result.put("message","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("message","注册异常");
        }
        return result;
    }
}
