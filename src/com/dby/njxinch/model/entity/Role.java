package com.dby.njxinch.model.entity;

import com.dby.njxinch.model.ClassCommon;

/**
 * Created by fgs on 2016/7/24.
 */
public class Role extends ClassCommon{
    private Integer roleId;
    private String roleName;
    private String roleIdenty;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleIdenty() {
        return roleIdenty;
    }

    public void setRoleIdenty(String roleIdenty) {
        this.roleIdenty = roleIdenty;
    }
}
