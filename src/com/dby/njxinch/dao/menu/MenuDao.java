package com.dby.njxinch.dao.menu;

import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.model.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/10/28.
 */
public interface MenuDao {
    void saveMenuData(Map<String, Object> result);

    void updateMenuData(Map<String, Object> result);

    List<FunctionMenus> checkMenuName(Map<String, Object> param);

    List<FunctionMenus> getMenusList(Map<String, Object> params);

    /**
     *  删除
     * @param params
     */
    void deletePowerMenu(Map<String, Object> params);

    /**
     * 增加
     * @param list
     */
    void addPowerMenu(List<RoleMenu> list);
}
