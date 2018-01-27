package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where userID = #{Id}")
    User findUserById(String id);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void addUser(User user);

    @Select("select * from user")
    List<User> getAllUsers();

    @Select("SELECT\n" +
            "\tproject.ProjectID,\n" +
            "\t`Name`,\n" +
            "\tCreateDate \n" +
            "FROM\n" +
            "\tproject\n" +
            "\tINNER JOIN mid_user_project m ON project.ProjectID = m.ProjectID\n" +
            "\tINNER JOIN `user` ON `user`.UserID = m.UserID \n" +
            "WHERE\n" +
            "\tUSER.UserID = #{id}")
    List<Project> getAllProject(String id);
}
