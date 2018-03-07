package com.dby.njxinch.dao.commonUtil;

import com.dby.njxinch.model.DataDictionary;

import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/3/9 21:19
 */
public interface CommonUtilDao {

    /**
     * 获取 所有 字典 key
     * @param param
     * @return
     */
    List<DataDictionary> queryListForKey(Map<String, String> param);
    /**
     * 获取 所有 字典 value
     * @param param
     * @return
     */
    List<DataDictionary> queryListForValue(Map<String, String> param);
}
