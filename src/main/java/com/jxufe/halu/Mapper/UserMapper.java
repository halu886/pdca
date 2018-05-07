package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserMapper {
    @Select("select * from user where userID = #{Id}")
    User findUserById(String id);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    @Options(useGeneratedKeys=true,keyProperty="userID", keyColumn="UserID")
    int addUser(User user);

    @Select("select * from user")
    List<User> getAllUsers();

    @Select("SELECT\n" +
            "\tRoleName\n" +
            "FROM\n" +
            "\tUSER us\n" +
            "LEFT JOIN mid_user_role ur ON ur.UserID = us.UserID\n" +
            "LEFT JOIN role rl ON ur.RoleID = rl.RoleID\n" +
            "WHERE\n" +
            "\tus.UserID = #{Id}")
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
            "\tus.UserID = #{Id}")
    Set<String> getPermissionById(String id);

    @Insert("INSERT INTO mid_user_role (UserID, RoleID)\n" +
            "VALUES(#{0},#{1})")
    int addRoleOfUser(String userId, String roleId);

    @Select("select * from user where userName = #{0}")
    User findUserByName(String username);

    @Select("SELECT\n" +
            "\tcount(*)\n" +
            "FROM\n" +
            "\t`user`\n" +
            "LEFT JOIN mid_user_project AS mup ON `user`.UserID = mup.UserID\n" +
            "LEFT JOIN project ON mup.ProjectID = project.ProjectID\n" +
            "WHERE\n" +
            "\t`user`.UserID = #{userID}")
    int countOfAllProject(String userID);

    @Select("SELECT\n" +
            "\ttype,\n" +
            "\tSUM(taskCount) AS count\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tcount(task.Progress) AS taskCount,\n" +
            "\t\tIF (\n" +
            "\t\t\ttask.Progress = 100,\n" +
            "\t\t\t'handler',\n" +
            "\t\t\t'unHandler'\n" +
            "\t\t) AS type\n" +
            "\t\tFROM\n" +
            "\t\t\t`user`\n" +
            "\t\tINNER JOIN mid_user_project AS mup ON `user`.UserID = mup.UserID\n" +
            "\t\tINNER JOIN project ON mup.ProjectID = project.ProjectID\n" +
            "\t\tINNER JOIN task ON project.ProjectID = task.ProjectID\n" +
            "\t\tWHERE\n" +
            "\t\t\t`user`.UserID = #{userID}\n" +
            "\t\tGROUP BY\n" +
            "\t\t\ttask.Progress\n" +
            "\t) AS handerTask\n" +
            "GROUP BY\n" +
            "\ttype")
    List<Map> countOfTask(String userID);


        @Select("SELECT\n" +
                "\tCOUNT(*)\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tproject.ProjectID,\n" +
                "\t\t\tSUM(task.NodeProgress)\n" +
                "\t\tFROM\n" +
                "\t\t\t`user`\n" +
                "\t\tLEFT JOIN mid_user_project AS mup ON `user`.UserID = mup.UserID\n" +
                "\t\tLEFT JOIN project ON mup.ProjectID = project.ProjectID\n" +
                "\t\tLEFT JOIN task ON project.ProjectID = task.ProjectID\n" +
                "\t\tWHERE\n" +
                "\t\t\t`user`.UserID = #{userID}\n" +
                "\t\tAND task.PTaskID IS NULL\n" +
                "\t\tAND task.Progress = 100\n" +
                "\t\tGROUP BY\n" +
                "\t\t\tproject.ProjectID\n" +
                "\t) AS a")
    int countOfHandleProject(String userID);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\t`user`\n" +
            "INNER JOIN mid_user_role ON `user`.UserID = mid_user_role.UserID\n" +
            "INNER JOIN role ON role.RoleID = mid_user_role.RoleID\n" +
            "WHERE\n" +
            "\trole.RoleName = #{role}")
    List<User> findUserByRole(String role);
}
