package com.jxufe.halu.Mapper;

import com.jxufe.halu.model.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper{

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\trole\n" +
            "WHERE\n" +
            "\tRoleName = #{name}")
    Role findRoleByName(String name);

}
