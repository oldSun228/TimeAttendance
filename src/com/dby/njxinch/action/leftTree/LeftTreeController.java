package com.dby.njxinch.action.leftTree;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.model.User;
import com.dby.njxinch.services.LeftTreeService;
import com.dby.njxinch.util.RedisUtil;
import com.dby.njxinch.util.SerializeUtil;
import com.dby.njxinch.util.StringUtils;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/7/3.
 */
@Controller
@RequestMapping("/leftTree")
public class LeftTreeController {

    @Resource
    private LeftTreeService leftTreeService;

    /**
     * 获取菜单列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMenusList", method = RequestMethod.POST)
    @ResponseBody
    public Object getMenusList(HttpServletRequest request) {
        String inWays=request.getParameter("inWays");
        String roleIdenty=(String)request.getSession().getAttribute(CommonConstants.USER_SESSION_IDENTY);
        //进入方式  如果不为空  则 是 角色菜单编辑页面进来的
        if(StringUtils.isNotEmpty(inWays)){
            roleIdenty=inWays;
        }
        Jedis jedis = RedisUtil.getJedis();
        byte[] in = jedis.get(CommonConstants.USER_MENU_RESOURCE.getBytes());
        Map<String,JSONArray> powerMap=(Map<String,JSONArray>) SerializeUtil.deserialize(in);
        List<FunctionMenus> allMenusList=powerMap.get(roleIdenty);
        return allMenusList;
    }

    /**
     * 获取子菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getChildMenus", method = RequestMethod.POST)
    @ResponseBody
    public Object getChildMenus(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        System.out.println(request.getParameter("flag"));
        result.put("status", "1");
        result.put("menuId", request.getParameter("menuId"));
        List<FunctionMenus> menusChildList = leftTreeService.getChildMenus(result);
        result.put("menusChildList", menusChildList);
        return result;
    }


}
