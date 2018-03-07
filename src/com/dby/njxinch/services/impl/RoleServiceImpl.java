package com.dby.njxinch.services.impl;

import com.dby.njxinch.dao.role.RoleDao;
import com.dby.njxinch.model.UserRoleRelation;
import com.dby.njxinch.model.entity.Role;
import com.dby.njxinch.services.RoleService;
import com.dby.njxinch.services.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    /**
     * 获取角色分页列表
     *
     * @param params
     * @return
     */
    @Override
    public List<Role> getPageList(Map<String, Object> params) {
        return roleDao.getPageList(params);
    }

    /**
     * 获取列表总数
     *
     * @return
     */
    @Override
    public int getTotalCount(Map<String, Object> params) {
        return roleDao.getTotalCount(params);
    }

    /**
     * 角色新增操作
     *
     * @param result
     */
    @Override
    public void saveRoleData(Map<String, Object> result) {
        roleDao.saveRoleData(result);
    }


    /**
     * check 角色名称是否存在
     *
     * @param param
     * @return
     */
    @Override
    public List<Role> checkRoleName(Map<String, Object> param) {
        return roleDao.checkRoleName(param);
    }

    /**
     * 查看角色详情操作
     *
     * @param result
     * @return
     */
    @Override
    public Role detailRoleData(Map<String, Object> result) {

        return roleDao.detailRoleData(result);
    }

    /**
     * 修改角色操作
     *
     * @param result
     * @return
     */
    @Override
    public void updateRoleData(Map<String, Object> result) {
        roleDao.updateRoleData(result);
    }

    /**
     * 角色删除操作
     *
     * @param result
     * @return
     */
    @Override
    public void delRoleData(Map<String, Object> result) {
        roleDao.delRoleData(result);
    }

    /**
     * 获取角色list
     * @return
     */
    @Override
    public List<Role> getRoleList(Map<String,Object> params) {
        return roleDao.getRoleList(params);
    }

    /**
     * 通过账号 获取角色list
     * @param params
     * @return
     */
    @Override
    public List<Role> getRoleListByAccount(Map<String, Object> params) {
        return roleDao.getRoleListByAccount(params);
    }

    /**
     * 已经存在的角色
     * @param params
     * @return
     */
    @Override
    public List<Role> existRoleList(Map<String, Object> params) {
        return roleDao.existRoleList(params);
    }

    /**
     * 不存在的角色
     * @param params
     * @return
     */
    @Override
    public List<Role> noExistRoleList(Map<String, Object> params) {
        return roleDao.noExistRoleList(params);
    }

    /**
     * 删除用户角色关系
     * @param params
     */
    @Override
    public void editRoleUserRelation(Map<String, Object> params) {
        JSONArray array= JSONArray.fromObject(params.get("roleIdArray"));
        List<UserRoleRelation> relationList = JSONArray.toList(array, UserRoleRelation.class);// 过时方法
        Map<String, Object> param = new HashMap<>();
        roleDao.delRoleUserRelationByUserId(params);
        for(UserRoleRelation o:relationList){
            o.setCreateBy((String)params.get("createBy"));
        }
        roleDao.insertRoleUserRelationByUserId(relationList);
    }


}
