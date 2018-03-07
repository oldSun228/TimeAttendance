package com.dby.njxinch.services;

import com.dby.njxinch.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> doLogin(HttpServletRequest request,String loginName, String password) throws Exception;

    /**
     * 获取用户pagelist
     * @param params
     * @return
     */
    List<User> getUserPageList(Map<String, Object> params);

    /**
     * 获取总数量
     * @param params
     * @return
     */
    int getUserTotalCount(Map<String, Object> params);


    /**
     * 账号check
     * @param param
     * @return
     */
    List<User> checkAccountName(Map<String, Object> param);
}
