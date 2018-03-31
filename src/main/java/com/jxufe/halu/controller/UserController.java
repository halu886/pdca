package com.jxufe.halu.controller;

import com.jxufe.halu.model.User;
import com.jxufe.halu.service.IUserService;
import com.jxufe.halu.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    IUserService service = new UserServiceImpl();
    private Set role;

    @RequestMapping(value = "/countOfIndex",method = RequestMethod.GET)
    public @ResponseBody Map countOfIndex(){
        Map  result  = new HashMap();
        result.put("status",false);
        result.put("message","查询失败");
        try{
            Session session = SecurityUtils.getSubject().getSession();

            User user = (User) session.getAttribute("user");
            Map count = service.countOfIndex(user.getUserID());
            result.put("status",true);
            result.put("message","查询成功");
            result.put("data",count);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

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
