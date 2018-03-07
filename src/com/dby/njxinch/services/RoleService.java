package com.dby.njxinch.services;

import com.dby.njxinch.model.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    /**
     * 获取角色分页列表
     *
     * @param params
     * @return
     */
    List<Role> getPageList(Map<String, Object> params);

    /**
     * 获取列表总数
     *
     * @return
     */
    int getTotalCount(Map<String, Object> params);

    /**
     * 角色新增操作
     *
     * @param result
     */
    void saveRoleData(Map<String, Object> result);

    /**
     * check 角色名称是否存在
     *
     * @param param
     * @return
     */
    List<Role> checkRoleName(Map<String, Object> param);

    /**
     * 查看角色详情操作
     *
     * @param result
     * @return
     */
    Role detailRoleData(Map<String, Object> result);

    /**
     * 修改角色操作
     *
     * @param result
     * @return
     */
    void updateRoleData(Map<String, Object> result);

    /**
     * 角色删除操作
     *
     * @param result
     * @return
     */
    void delRoleData(Map<String, Object> result);

    /**
     *  获取角色list
     * @return
     */
    List<Role> getRoleList(Map<String,Object> params);

    /**
     *  通过账号 获取角色list
     * @return
     */
    List<Role> getRoleListByAccount(Map<String, Object> params);

    /**
     * 已经存在的角色
     * @param params
     * @return
     */
    List<Role> existRoleList(Map<String, Object> params);

    /**
     * 不存在的角色
     * @param params
     * @return
     */
    List<Role> noExistRoleList(Map<String, Object> params);

    /**
     * 编辑用户角色关系
     * @param params
     * @return
     */
    void editRoleUserRelation(Map<String, Object> params);
}
