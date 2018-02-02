package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where userID = #{Id}")
    User findUserById(String id);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void addUser(User user);

    @Select("select * from user")
    List<User> getAllUsers();

}
