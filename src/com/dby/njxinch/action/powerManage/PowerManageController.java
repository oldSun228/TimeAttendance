package com.dby.njxinch.action.powerManage;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.User;
import com.dby.njxinch.services.PowerService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fgs on 2016/8/13.
 */
@Controller
@RequestMapping("/powerManage")
public class PowerManageController {

    @Autowired
    private PowerService powerService;

    /**
     * 跳转到 powerManagePage
     *
     * @return
     */
    @RequestMapping(value = "/toPowerIndexPage", method = RequestMethod.GET)
    public String toMiddlePage() {
        return "systemManage/powerManage/powerIndex";
    }

    /**
     * 增加or修改操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdatePowerMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdatePowerMenu(HttpServletRequest request) throws Exception {
        //页面请求参数
        String roleId=request.getParameter("roleId");
        String identy=request.getParameter("identy");
        String node = request.getParameter("nodesArray");
        JSONArray data = JSONArray.fromObject(node);
        User user=(User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        Map<String,Object> params= new HashMap<>();
        params.put("nodes", data);
        params.put("roleId",roleId);
        params.put("createBy",user.getAccount());
        params.put("identy",identy);
        Map<String, Object> result = null;
        try {
            result = new HashMap<>();
            powerService.editPowerMenu(params);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
