package com.dby.njxinch.services;

import com.dby.njxinch.model.FunctionMenus;

import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/7/3.
 */
public interface LeftTreeService {
    /**
     * 获取所有父节点
     * @param params
     * @return
     */
    List<FunctionMenus> getFirstMenus(Map<String,Object> params);

    /**
     * 获取所有子节点
     * @param params
     * @return
     */
    List<FunctionMenus> getChildMenus(Map<String,Object> params);
}
