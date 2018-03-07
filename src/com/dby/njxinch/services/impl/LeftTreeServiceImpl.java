package com.dby.njxinch.services.impl;

import com.dby.njxinch.dao.leftTree.LeftTreeDao;
import com.dby.njxinch.model.FunctionMenus;
import com.dby.njxinch.services.LeftTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/7/3.
 */
@Service("leftTreeService")
public class LeftTreeServiceImpl implements LeftTreeService{

    @Autowired
    private LeftTreeDao leftTreeDao;

    /**
     * 获取所有父节点
     * @param params
     * @return
     */
    @Override
    public List<FunctionMenus> getFirstMenus(Map<String,Object> params) {
        return leftTreeDao.getFirstMenus(params);
    }

    /**
     * 获取所有子节点
     * @param params
     * @return
     */
    @Override
    public List<FunctionMenus> getChildMenus(Map<String,Object> params) {
        return leftTreeDao.getChildMenus(params);
    }
}
