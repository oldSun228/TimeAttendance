package com.dby.njxinch.services;

import com.dby.njxinch.model.FunctionMenus;

import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/10/28.
 */
public interface MenuService {
    /**
     * 保存
     * @param result
     */
    void saveMenuData(Map<String, Object> result);

    /**
     * 修改
     * @param result
     */
    void updateMenuData(Map<String, Object> result);

    /**
     * 验证是否已存在
     * @param param
     * @return
     */
    List<FunctionMenus> checkMenuName(Map<String, Object> param);

    /**
     * 获取菜单list
     * @return
     */
    List<FunctionMenus> getMenusList(Map<String, Object> params);

    /**
     * 编辑 权限菜单
     * @param params
     */
    void editPowerMenu(Map<String, Object> params);

}
