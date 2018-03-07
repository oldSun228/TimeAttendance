package com.dby.njxinch.services.impl;

import com.dby.njxinch.dao.power.PowerDao;
import com.dby.njxinch.model.entity.RoleMenu;
import com.dby.njxinch.services.CommonUtilService;
import com.dby.njxinch.services.PowerService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author fgs
 * @Date 2017/4/10 20:23
 */
@Service("powerService")
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerDao powerDao;

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
        powerDao.deletePowerMenu(params);
        if(list.size() > 0){
            powerDao.addPowerMenu(list);
        }
        commonUtilService.initMap(identy);
    }
}
