package com.jxufe.halu.Dao;

import com.jxufe.halu.model.Role;

public interface IRoleDao {
    public Role findRoleById(String id);
    public  Role findRoleByName(String name);
}
