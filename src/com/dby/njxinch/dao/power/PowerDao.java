package com.dby.njxinch.dao.power;

import com.dby.njxinch.model.entity.RoleMenu;

import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/4/10 20:23
 */
public interface PowerDao {
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
