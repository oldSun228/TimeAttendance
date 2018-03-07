package com.dby.njxinch.services.impl;

import com.dby.njxinch.dao.menu.MenuDao;
import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.model.entity.RoleMenu;
import com.dby.njxinch.services.CommonUtilService;
import com.dby.njxinch.services.MenuService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/10/28.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Override
    public void saveMenuData(Map<String, Object> result) {
        menuDao.saveMenuData(result);
    }

    @Override
    public void updateMenuData(Map<String, Object> result) {
        menuDao.updateMenuData(result);
    }

    @Override
    public List<FunctionMenus> checkMenuName(Map<String, Object> param) {
        return menuDao.checkMenuName(param);
    }

    /**
     * 获取菜单list
     * @param params
     * @return
     */
    @Override
    public List<FunctionMenus> getMenusList(Map<String, Object> params) {
        return menuDao.getMenusList(params);
    }

    @Autowired
    private CommonUtilService commonUtilService;

    /**
     * 编辑 权限菜单
     * @param params
     */
    @Override
    public void editPowerMenu(Map<String, Object> params) {
        String createBy=(String) params.get("createBy");
        String identy=(String) params.get("identy");
        JSONArray array= (JSONArray) params.get("nodes");
        List<RoleMenu> list=JSONArray.toList(array, RoleMenu.class);
        if(list.size() > 0){
            for(RoleMenu o:list){
                o.setCreateBy(createBy);
            }
        }
        menuDao.deletePowerMenu(params);
        if(list.size() > 0){
            menuDao.addPowerMenu(list);
        }
        commonUtilService.initMap(identy);
    }

}
