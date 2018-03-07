package com.dby.njxinch.action.menuManage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.model.User;
import com.dby.njxinch.services.LeftTreeService;
import com.dby.njxinch.services.MenuService;
import com.dby.njxinch.util.RedisUtil;
import com.dby.njxinch.util.SerializeUtil;
import com.dby.njxinch.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/7/24.
 */
@Controller
@RequestMapping("/menuManage")
public class MenuManageController {

    @Resource
    private LeftTreeService leftTreeService;

    @Resource
    private MenuService menuService;

    /**
     * 跳转到 menuIndex
     *
     * @return
     */
    @RequestMapping(value = "/toMenuIndexPage", method = RequestMethod.GET)
    public String toRoleIndexPage() {
        return "systemManage/menuManage/menuIndex";
    }

    /**
     * 跳转到 菜单弹出层
     *
     * @return
     */
    @RequestMapping(value = "/toMenuAlertPage", method = RequestMethod.POST)
    public String toMenuAlertPage(HttpServletRequest request,Model model) {
        model.addAttribute("selectedId",request.getParameter("selectedId"));
        return "systemManage/menuManage/menuAlert";
    }


    /**
     * 获取总菜单树
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMenusList", method = RequestMethod.POST)
    @ResponseBody
    public Object getMenusList(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        Jedis jedis = RedisUtil.getJedis();
        byte[] in = jedis.get(CommonConstants.MENU_RESOURCE.getBytes());
        if (in == null) {
            Map<String, Object> params = new HashMap<>();
            //获取所有父节点
            List<FunctionMenus> menusList = leftTreeService.getFirstMenus(params);
            //获取 总 树 结构
            List<FunctionMenus> allMenusList = getMenusList(menusList);
            for (FunctionMenus o : allMenusList) {
                JSONObject jsonObject = new JSONObject();
                if (o.getParentMenuId() == 0) {
                    jsonObject.put("id", o.getMenuId());
                    jsonObject.put("menuSn", o.getMenuSn());
                    jsonObject.put("pid", o.getParentMenuId());
                    jsonObject.put("name", o.getMenuName());
                    jsonObject.put("menuUrl", o.getMenuUrl());
                    jsonObject.put("menuPic", o.getMenuPic());
                    jsonObject.put("status", o.getStatus());
                    jsonObject.put("remark", o.getRemark());
                    //jsonObject.put("iconSkin","pIcon01");

                    //判断所选择节点是否是父节点，如果是设置isParent属性为true,不是设置为false
                    if (o.getFunctionMenusList() != null) {
                        //格式化 子节点 数据
                        JSONArray subJsonArray = getSubJsonData(o.getFunctionMenusList());
                        jsonObject.put("subChildren", subJsonArray);
                    }
                }
                jsonArray.add(jsonObject);
            }
            jedis.set(CommonConstants.MENU_RESOURCE.getBytes(), SerializeUtil.serialize(jsonArray));
            in = jedis.get(CommonConstants.MENU_RESOURCE.getBytes());
        }
        jsonArray = (JSONArray) SerializeUtil.deserialize(in);
        return jsonArray;
    }

    /**
     * 递归获取子节点方法 list
     *
     * @param menusList
     * @return
     */
    public List<FunctionMenus> getMenusList(List<FunctionMenus> menusList) {
        for (FunctionMenus o : menusList) {
            Map<String, Object> params = new HashMap<>();
            params.put("menuId", o.getMenuId());
            //根据 父节点id 找到对应的所有子节点
            List<FunctionMenus> menusChildList = leftTreeService.getChildMenus(params);
            if(menusChildList.size() > 0){//如果有子节点 则再次 查子节点的子节点
                o.setFunctionMenusList(menusChildList);
                getMenusList(menusChildList);
            }else{
                o.setFunctionMenusList(null);
            }
        }
        return menusList;
    }

    /**
     * 递归获取子节点方法 jsonArray格式
     *
     * @param functionMenusList
     * @return
     */
    public JSONArray getSubJsonData(List<FunctionMenus> functionMenusList) {
        JSONArray subJsonArray = new JSONArray();
        for (FunctionMenus o : functionMenusList) {
            JSONObject subchild = new JSONObject();
            subchild.put("id", o.getMenuId());
            subchild.put("menuSn", o.getMenuSn());
            subchild.put("pid", o.getParentMenuId());
            subchild.put("name", o.getMenuName());
            subchild.put("menuUrl", o.getMenuUrl());
            subchild.put("menuPic", o.getMenuPic());
            subchild.put("status", o.getStatus());
            subchild.put("remark", o.getRemark());
            if (o.getFunctionMenusList() != null) {
                JSONArray sub = getSubJsonData(o.getFunctionMenusList());
                subchild.put("subChildren", sub);
            }
            subJsonArray.add(subchild);
        }
        return subJsonArray;
    }


    /**
     * 增加or修改操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdateMenu(HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        try {
            result = new HashMap<>();
            //获取角色id 不为则为修改 否则为增加
            result.put("menuSn", request.getParameter("menuSn"));
            result.put("parentMenuId", request.getParameter("parentMenuId")!=""?request.getParameter("parentMenuId"):0);
            result.put("menuPic", URLDecoder.decode(request.getParameter("menuPic"), "UTF-8"));
            result.put("menuName", request.getParameter("menuName"));
            result.put("menuUrl", request.getParameter("menuUrl"));
            result.put("status", request.getParameter("status"));
            result.put("remark", request.getParameter("remark"));
            User userBean = (User) request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
            result.put("createBy", userBean.getCreateBy());
            if (StringUtils.isNotEmpty(request.getParameter("menuId"))) {
                result.put("menuId", request.getParameter("menuId"));
                menuService.updateMenuData(result);
                result.put("msg", "菜单修改成功");
            } else {
                menuService.saveMenuData(result);
                result.put("msg", "菜单增加成功");
            }
            Jedis jedis = RedisUtil.getJedis();
            jedis.del(CommonConstants.MENU_RESOURCE);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    /**
     * 跳转到 menuPicIndex
     *
     * @return
     */
    @RequestMapping(value = "/toMenuPicIndexPage", method = RequestMethod.POST)
    public String toMenuPicIndexPage() {
        return "systemManage/menuManage/menuPicIndexPage";
    }

    /**
     * check 菜单名 是否已存在
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkMenuName", method = RequestMethod.POST)
    @ResponseBody
    public Object checkMenuName(HttpServletRequest request) throws Exception {
        Map<String, Object> param = new HashMap<>();
        //获取角色名称
        if (StringUtils.isNotEmpty(request.getParameter("menuId"))) {
            param.put("menuId", Integer.valueOf(request.getParameter("menuId")));
            param.put("menuName", request.getParameter("menuName"));
            List<FunctionMenus> menuList = menuService.checkMenuName(param);
            if(menuList.size() != 1){//判断是否在修改的时候 添加了 已存在的名称
                param.put("menuId", null);
                List<FunctionMenus> subMenuList = menuService.checkMenuName(param);
                if(subMenuList.size() > 0){
                    return false;//表示不通过
                }
            }
        }else{
            param.put("menuName", request.getParameter("menuName"));
            List<FunctionMenus> menuList = menuService.checkMenuName(param);
            if(menuList.size() > 0){
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
    @RequestMapping(value = "/saveOrUpdatePowerMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdatePowerMenu(HttpServletRequest request) throws Exception {
        //页面请求参数
        String roleId=request.getParameter("roleId");
        String identy=request.getParameter("identy");
        String node = request.getParameter("nodesArray");
        net.sf.json.JSONArray data = net.sf.json.JSONArray.fromObject(node);
        User user=(User)request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
        Map<String,Object> params= new HashMap<>();
        params.put("nodes", data);
        params.put("roleId",roleId);
        params.put("createBy",user.getAccount());
        params.put("identy",identy);
        Map<String, Object> result = null;
        try {
            result = new HashMap<>();
            menuService.editPowerMenu(params);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
