package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Project;
import com.jxufe.halu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    @Select("select * from user where userID = #{Id}")
    User findUserById(String id);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void addUser(User user);

    @Select("select * from user")
    List<User> getAllUsers();

    @Select("SELECT\n" +
            "\tRoleName\n" +
            "FROM\n" +
            "\tUSER us\n" +
            "LEFT JOIN mid_user_role ur ON ur.UserID = us.UserID\n" +
            "LEFT JOIN role rl ON ur.RoleID = rl.RoleID\n" +
            "WHERE\n" +
            "\tus.UserID = ${Id}")
    Set<String> getRoleById(String id);

    @Select("SELECT\n" +
            "\tPermissionName\n" +
            "FROM\n" +
            "\tUSER us\n" +
            "LEFT JOIN mid_user_role ur ON ur.UserID = us.UserID\n" +
            "LEFT JOIN role rl ON ur.RoleID = rl.RoleID\n" +
            "LEFT JOIN mid_role_permission rp ON rl.RoleID = rp.RoleID\n" +
            "LEFT JOIN permission pms ON rp.PermissionID = pms.PermissionID\n" +
            "WHERE\n" +
            "\tus.UserID = ${Id}")
    Set<String> getPermissionById(String id);
}
