package com.dby.njxinch.model;

/**
 * @Author fgs
 * @Date 2017/4/21 22:39
 */
public class UserRoleRelation extends ClassCommon{
    private String relationId;
    private String userId;
    private String roleId;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
