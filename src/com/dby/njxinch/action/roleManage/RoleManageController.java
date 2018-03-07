package com.dby.njxinch.action.roleManage;


import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.User;
import com.dby.njxinch.model.UserRoleRelation;
import com.dby.njxinch.model.entity.Role;
import com.dby.njxinch.services.RoleService;
import com.dby.njxinch.util.StringUtils;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/7/24.
 */
@Controller
@RequestMapping("/roleManage")
public class RoleManageController {
    @Resource
    private RoleService roleservice;

    /**
     * 跳转到 roleIndex
     *
     * @return
     */
    @RequestMapping(value = "/toRoleIndexPage", method = RequestMethod.GET)
    public String toRoleIndexPage() {
        return "systemManage/roleManage/roleIndex";
    }

    /**
     * 获取角色PageList
     */
    @RequestMapping(value = "/getRolePageList", method = RequestMethod.POST)
    @ResponseBody
    public Object getRolePageList(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("startPage", Integer.valueOf(request.getParameter("start")));
        params.put("pageSize", Integer.valueOf(request.getParameter("length")));
        if(StringUtils.isNotEmpty(request.getParameter("roleName"))){
            params.put("roleName", request.getParameter("roleName"));
        }
        if(StringUtils.isNotEmpty(request.getParameter("status"))){
            params.put("status", request.getParameter("status"));
        }

        List<Role> rolePageList = roleservice.getPageList(params);
        int totalCount = roleservice.getTotalCount(params);
        params.put("recordsFiltered", totalCount);
        params.put("recordsTotal", totalCount);
        params.put("draw", request.getParameter("draw"));
        params.put("data", rolePageList);


        System.out.println(">>>>>>>>>>>>>>>>>>>>" + request.getParameter("start"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + request.getParameter("length"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + request.getParameter("draw"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + request.getParameter("recordsTotal"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + request.getParameter("recordsFiltered"));
        return params;
    }

    /**
     * 获取角色List
     */
    @RequestMapping(value = "/getRoleListByAccount", method = RequestMethod.POST)
    @ResponseBody
    public Object getRoleListByAccount(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String,Object>();
        User userBean = (User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        params.put("account", userBean.getAccount());
        params.put("status", request.getParameter("status"));

        List<Role> roleList = roleservice.getRoleListByAccount(params);
        Map<String, Object> result = new HashMap<String,Object>();
        result.put("isSuccess", true);
        result.put("roleList",roleList);
        return result;
    }

    /**
     * 增加or修改操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateRole", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdateRole(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        //获取角色id 不为则为修改 否则为增加
        result.put("roleName", request.getParameter("roleName"));
        result.put("status", request.getParameter("status"));
        result.put("remark", request.getParameter("remark"));
        User userBean = (User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        result.put("createBy", userBean.getCreateBy());
        if (StringUtils.isNotEmpty(request.getParameter("roleId"))) {
            result.put("roleId", request.getParameter("roleId"));
            roleservice.updateRoleData(result);
            result.put("msg", "角色修改成功");
        } else {
            roleservice.saveRoleData(result);
            result.put("msg", "角色增加成功");
        }
        result.put("isSuccess", true);
        return result;
    }

    /**
     * check 角色名 是否已存在
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkRoleName", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRoleName(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        //获取角色名称
        if (StringUtils.isNotEmpty(request.getParameter("roleId"))) {
            param.put("roleId", Integer.valueOf(request.getParameter("roleId")));
            param.put("roleName", request.getParameter("roleName"));
            List<Role> roleList = roleservice.checkRoleName(param);
            if(roleList.size() != 1){
                param.put("roleId", null);
                List<Role> subRoleList = roleservice.checkRoleName(param);
                if(subRoleList.size() > 0){
                    return false;//表示不通过
                }
            }
        }else{
            param.put("roleName", request.getParameter("roleName"));
            List<Role> roleList = roleservice.checkRoleName(param);
            if(roleList.size() > 0){
                return false;//表示不通过
            }
        }
        return true;//表示通过
    }


    /**
     * 查看详情操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detailForRole", method = RequestMethod.POST)
    @ResponseBody
    public Object detailForRole(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        //获取角色id
        if (StringUtils.isNotEmpty(request.getParameter("roleId"))) {
            result.put("roleId", request.getParameter("roleId"));
            Role roleBean = roleservice.detailRoleData(result);
            result.put("msg", "查看详情成功");
            result.put("isSuccess", true);
            result.put("result", roleBean);
        } else {
            result.put("msg", "查看详情失败");
            result.put("isSuccess", false);
        }
        return result;
    }

    /**
     * 删除操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delForRole", method = RequestMethod.POST)
    @ResponseBody
    public Object delForRole(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        //获取角色id
        if (StringUtils.isNotEmpty(request.getParameter("roleId"))) {
            result.put("roleId", request.getParameter("roleId"));
            roleservice.delRoleData(result);
            result.put("msg", "角色删除成功");
            result.put("isSuccess", true);
        } else {
            result.put("msg", "角色删除失败");
            result.put("isSuccess", false);
        }
        return result;
    }


    /**
     * 获取已存在角色List
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    @ResponseBody
    public Object getRoleList(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<String,Object>();
        if(StringUtils.isNotEmpty(request.getParameter("userId"))){
            params.put("userId", request.getParameter("userId"));
        }

        List<Role> existRoleList = roleservice.existRoleList(params);
        List<Role> noExistRoleList = roleservice.noExistRoleList(params);
        params.put("existRoleList",existRoleList);
        params.put("noExistRoleList",noExistRoleList);
        params.put("isSuccess",true);
        return params;
    }

    /**
     * 保存用户已选角色
     */
    @RequestMapping(value = "/saveRoleUserRelation", method = RequestMethod.POST)
    @ResponseBody
    public Object saveRoleUserRelation(HttpServletRequest request) throws Exception {
        User user=(User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("roleIdArray",request.getParameter("roleIdArray"));
        params.put("userId",request.getParameter("userId"));
        params.put("createBy",user.getAccount());
        try {
            roleservice.editRoleUserRelation(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
       //批量增加
        params.put("isSuccess",true);
        return params;
    }

}
