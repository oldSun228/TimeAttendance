package com.dby.njxinch.action.dictionaryManage;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.model.DataDictionary;
import com.dby.njxinch.model.DictionaryCode;
import com.dby.njxinch.model.User;
import com.dby.njxinch.model.Ztree;
import com.dby.njxinch.services.DictionaryService;
import com.dby.njxinch.util.RedisUtil;
import com.dby.njxinch.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/1/21 14:14
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 跳转到 字典首页
     * @return
     */
    @RequestMapping(value = "/toDictionaryPage", method = RequestMethod.GET)
    public String toMiddlePage() {
        return "systemManage/dictionaryManage/dictionaryIndex";
    }

    /**
     * 字典数据
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDictionaryList", method = RequestMethod.POST)
    @ResponseBody
    public List<Ztree> getDictionaryList(HttpServletRequest request) throws Exception {
        //获取主父节点
        List<Ztree> list = dictionaryService.getDictionaryList();
        return list;
    }

    /**
      * 删除
      *
      * @param request
      * @return
      * @throws Exception
      */
    @RequestMapping(value = "/delDictionary", method = RequestMethod.POST)
    @ResponseBody
    public Object delDictionary(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("dicId",request.getParameter("dicId"));
        try {
            dictionaryService.delDictionary(result);
            result.put("isSuccess", true);
            result.put("msg", "操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }


    /**
     * 增加or修改操作
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveOrUpdateDictionary", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdateDictionary(HttpServletRequest request) throws Exception {
        Map<String, Object> result = null;
        try {
            result = new HashMap<>();
            //获取字典id 不为空 则为修改 否则为增加

            result.put("parentId", request.getParameter("parentId"));
            result.put("parentName", request.getParameter("parentName"));
            result.put("name", request.getParameter("name"));
            result.put("code", request.getParameter("code"));
            result.put("identy", request.getParameter("identy"));
            result.put("description", request.getParameter("description"));
            result.put("status", request.getParameter("status"));
            result.put("remark", request.getParameter("remark"));
            User userBean = (User) request.getSession().getAttribute(CommonConstants.USER_SESSION_KEY);
            result.put("createBy", userBean.getCreateBy());
            if (StringUtils.isNotEmpty(request.getParameter("dicId"))) {
                result.put("dicId", request.getParameter("dicId"));
                dictionaryService.updateDictionaryData(result);
                result.put("msg", "节点修改成功");
            } else {
                dictionaryService.saveDictionaryData(result);
                result.put("msg", "节点增加成功");
            }
//            Jedis jedis = RedisUtil.getJedis();
//            jedis.del(CommonConstants.LEFT_TREE);
//            jedis.del(CommonConstants.MENU_RESOURCE);
            result.put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
