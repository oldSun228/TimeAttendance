package com.dby.njxinch.services.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.dao.commonUtil.CommonUtilDao;
import com.dby.njxinch.model.DataDictionary;
import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.model.entity.Role;
import com.dby.njxinch.services.CommonUtilService;
import com.dby.njxinch.services.LeftTreeService;
import com.dby.njxinch.services.MenuService;
import com.dby.njxinch.services.RoleService;
import com.dby.njxinch.util.RedisUtil;
import com.dby.njxinch.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/3/9 21:09
 */
@Service("commonUtilService")
public class CommonUtilServiceImpl implements CommonUtilService {

    @Autowired
    private CommonUtilDao commonUtilDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    private Map<String, List<DataDictionary>> configMap = new HashMap<>();

    private Map<String,  List<FunctionMenus>> powerMap = new HashMap<>();

    @PostConstruct
    private void initData() {
        // 字典map
        initConfigMap();
        // 权限map
        initPowerMap();
    }

    /**
     * 设置 map 值
     */
    public void initConfigMap() {
        configMap.clear();    //清空内容
        Map<String, String> param = new HashMap<>();
        List<DataDictionary> keyList = commonUtilDao.queryListForKey(param);
        List<DataDictionary> valueList = commonUtilDao.queryListForValue(param);
        for (DataDictionary k : keyList) {
            List<DataDictionary> dataDictionaryList = new ArrayList<>();
            String key = k.getIdenty();
            String keyDicId = String.valueOf(k.getDicId());
            for (DataDictionary v : valueList) {
                String childParentId = String.valueOf(v.getParentId());
                if (keyDicId.equals(childParentId)) {
                    dataDictionaryList.add(v);
                }
            }
            configMap.put(key, dataDictionaryList);
        }
    }

    /**
     * 设置 map 值
     */
    public void initPowerMap() {
        powerMap.clear();    //清空内容
        Map<String, Object> params = new HashMap<>();
        //获取所有角色
        List<Role> roleList=roleService.getRoleList(params);
        for(Role o:roleList){
            //获取该角色下的菜单
            params = new HashMap<>();
            params.put("roleIdenty",o.getRoleIdenty());
            params.put("status",1);
            //获取该角色下的所有父节点
            List<FunctionMenus> menusList = menuService.getMenusList(params);
            //获取 总菜单list
            List<FunctionMenus> allMenusList = getMenusList(menusList,o.getRoleIdenty());
            powerMap.put(o.getRoleIdenty(),allMenusList);
        }
        Jedis jedis = RedisUtil.getJedis();
        jedis.set(CommonConstants.USER_MENU_RESOURCE.getBytes(), SerializeUtil.serialize(powerMap));
    }

    /**
     * 递归获取 子菜单
     *
     * @param menusList
     * @return
     */
    public List<FunctionMenus> getMenusList(List<FunctionMenus> menusList,String roleIdenty) {
        for (FunctionMenus o : menusList) {
            Map<String, Object> params = new HashMap<>();
            params.put("status", "1");//状态为启用
            params.put("menuId", o.getMenuId());
            params.put("roleIdenty",roleIdenty);
            //获取到对应的字节点
            List<FunctionMenus> menusChildList = menuService.getMenusList(params);
            //如果nextChild 不等于 1 和 2 则说明 还有下一层；如果等于2 说明 这个是调用http开头的链接
            if (menusChildList.size() > 0) {
                //说明 此节点 有子节点
                o.setNextFlag("true");
                o.setFunctionMenusList(menusChildList);
                getMenusList(menusChildList,roleIdenty);
            } else {
                //说明 此节点 无子节点
                o.setNextFlag("false");
                o.setFunctionMenusList(null);
            }
        }
        return menusList;
    }


    @Override
    public Map<String, List<DataDictionary>> getDicMap() {
        return configMap;
    }

    @Override
    public String getDicName(String identy, String value) {
        if (configMap.containsKey(identy)) {
            for (DataDictionary o : configMap.get(identy)) {
                if (value.equals(o.getCode())) {
                    return o.getName();
                }
            }
        }
        return null;
    }

    @Override
    public List<DataDictionary> getDicList(String identy) {
        if (configMap.containsKey(identy)) {
            return configMap.get(identy);
        }
        return null;
    }

    @Override
    public void initMap(String identy) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleIdenty",identy);
        //获取所有角色
        List<Role> roleList=roleService.getRoleList(params);
        for(Role o:roleList){
            //获取该角色下的菜单
            params = new HashMap<>();
            params.put("roleIdenty",o.getRoleIdenty());
            params.put("status",1);
            //获取该角色下的所有父节点
            List<FunctionMenus> menusList = menuService.getMenusList(params);
            //获取 总菜单list
            List<FunctionMenus> allMenusList = getMenusList(menusList,o.getRoleIdenty());
            powerMap.put(o.getRoleIdenty(),allMenusList);
        }
        Jedis jedis = RedisUtil.getJedis();
        jedis.set(CommonConstants.USER_MENU_RESOURCE.getBytes(), SerializeUtil.serialize(powerMap));
    }
}
