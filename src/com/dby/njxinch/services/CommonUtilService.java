package com.dby.njxinch.services;

import com.dby.njxinch.model.DataDictionary;

import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2017/3/9 21:08
 */
public interface CommonUtilService {
    Map<String,List<DataDictionary>> getDicMap();

    String getDicName(String identy, String value);

    List<DataDictionary> getDicList(String identy);

    void initMap(String identy);


}
