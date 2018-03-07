package com.dby.njxinch.action.user;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.User;
import com.dby.njxinch.model.entity.Role;
import com.dby.njxinch.services.UserService;
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

@Controller
@RequestMapping("/userManage")
public class UserManageController {

    @Resource
    private UserService userService;

    /**
     * 跳转到 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/toUserIndex", method = RequestMethod.GET)
    public String toLogin() {
        return "systemManage/userManage/userIndexPage";
    }


    /**
     * 获取用户PageList
     */
    @RequestMapping(value = "/getUserPageList", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserPageList(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("startPage", Integer.valueOf(request.getParameter("start")));
        result.put("pageSize", Integer.valueOf(request.getParameter("length")));
        if(StringUtils.isNotEmpty(request.getParameter("userName"))){
            result.put("userName", request.getParameter("userName"));
        }
        if(StringUtils.isNotEmpty(request.getParameter("status"))){
            result.put("status", request.getParameter("status"));
        }

        List<User> userPageList = userService.getUserPageList(result);
        int totalCount = userService.getUserTotalCount(result);
        result.put("recordsFiltered", totalCount);
        result.put("recordsTotal", totalCount);
        result.put("draw", request.getParameter("draw"));
        result.put("data", userPageList);
        return result;
    }


    /**
     * check 账号 是否已存在
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkUserAccount", method = RequestMethod.POST)
    @ResponseBody
    public Object checkRoleName(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<>();
        //获取角色名称
        if (StringUtils.isNotEmpty(request.getParameter("userId"))) {
            param.put("userId", Integer.valueOf(request.getParameter("userId")));
            param.put("account", request.getParameter("account"));
            List<User> roleList = userService.checkAccountName(param);
            if(roleList.size() != 1){
                param.put("userId", null);
                List<User> subRoleList = userService.checkAccountName(param);
                if(subRoleList.size() > 0){
                    return false;//表示不通过
                }
            }
        }else{
            param.put("account", request.getParameter("account"));
            List<User> roleList = userService.checkAccountName(param);
            if(roleList.size() > 0){
                return false;//表示不通过
            }
        }
        return true;//表示通过
    }


    /**
     * 增加or修改操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateUser", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdateUser(HttpServletRequest request) throws Exception {
        //页面请求参数
        String userId=request.getParameter("userId");
        String userName=request.getParameter("userName");
        String sex=request.getParameter("sex");
        String telphone=request.getParameter("telphone");
        String idCard=request.getParameter("idCard");
        String account=request.getParameter("account");
        String entryTime=request.getParameter("entryTime");
        String education=request.getParameter("education");
        String graShool=request.getParameter("graShool");
        String wage=request.getParameter("wage");
        String address = request.getParameter("address");
        String remark = request.getParameter("remark");
        User user=(User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        Map<String,Object> params= new HashMap<>();
//        params.put("nodes", data);
//        params.put("roleId",roleId);
//        params.put("createBy",user.getAccount());
//        params.put("identy",identy);
        Map<String, Object> result = null;
        try {
            result = new HashMap<>();
//            powerService.editPowerMenu(params);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
