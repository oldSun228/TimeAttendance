package com.dby.njxinch.services.impl;

import com.dby.njxinch.dao.dictionary.DictionaryDao;
import com.dby.njxinch.model.DataDictionary;
import com.dby.njxinch.model.DictionaryCode;
import com.dby.njxinch.model.Ztree;
import com.dby.njxinch.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fgs on 2016/11/24.
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 获取字典树 数据
     * @return
     */
    @Override
    public List<Ztree> getDictionaryList() {
        List<Ztree> list= new ArrayList<Ztree>();
        List<DataDictionary> dataDictionaryList =dictionaryDao.getDictionaryList();
        for(DataDictionary o:dataDictionaryList){
            Ztree bean = new Ztree();
            bean.setId(o.getDicId());
            bean.setpId(o.getParentId());
            bean.setName(o.getName());
            bean.setCode(o.getCode());
            bean.setStatus(o.getStatus());
            bean.setIdenty(o.getIdenty());
            bean.setDescription(o.getDescription());
            bean.setRemark(o.getRemark());
            list.add(bean);
        }
        return list;
    }

    /**
     * 删除节点
     * @param params
     */
    @Override
    public void delDictionary(Map<String, Object> params) {
        dictionaryDao.delDictionary(params);
    }

    /**
     * 修改节点
     * @param params
     */
    @Override
    public void updateDictionaryData(Map<String, Object> params) {
        dictionaryDao.updateDictionaryData(params);
    }

    /**
     * 新增节点
     * @param params
     */
    @Override
    public void saveDictionaryData(Map<String, Object> params) {
        dictionaryDao.saveDictionaryData(params);
    }

}
