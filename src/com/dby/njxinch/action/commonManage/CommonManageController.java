package com.dby.njxinch.action.commonManage;

import com.dby.njxinch.model.DataDictionary;
import com.dby.njxinch.services.CommonUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/3/14 19:48
 * 类说明：获取字典的共通
 */
@Controller
@RequestMapping("/commonManage")
public class CommonManageController {

    @Autowired
    private CommonUtilService commonUtilService;

    /**
     * 获取 字典数据 常量
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDicMap", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,List<DataDictionary>> getDicMap(HttpServletRequest request) throws Exception {

        Map<String,List<DataDictionary>> dicMap = commonUtilService.getDicMap();
        return dicMap;
    }

    /**
     * 字典数据 名称
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDicName", method = RequestMethod.POST)
    @ResponseBody
    public String getDicName(HttpServletRequest request) throws Exception {
        String identy=request.getParameter("identy");
        String value=request.getParameter("valu");
        //获取主父节点
        String name = commonUtilService.getDicName(identy,value);
        return name;
    }

    /**
     * 字典数据 list
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDicList", method = RequestMethod.POST)
    @ResponseBody
    public List<DataDictionary> getDicList(HttpServletRequest request) throws Exception {
        String identy=request.getParameter("identy");
        //获取主父节点
        List<DataDictionary> list = commonUtilService.getDicList(identy);
        return list;
    }
}
