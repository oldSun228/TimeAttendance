package com.dby.njxinch.services.impl;

import com.dby.njxinch.common.CommonConstants;
import com.dby.njxinch.common.PasswordUtil;
import com.dby.njxinch.dao.user.UserDao;
import com.dby.njxinch.model.User;
import com.dby.njxinch.services.UserService;
import com.dby.njxinch.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> doLogin(HttpServletRequest request, String loginName, String password) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        User admin = getAccountByName(loginName);
        if (null != admin) {
            if (PasswordHash.validatePassword(password,admin.getPassword())) {
                if("2".equals(admin.getStatus())){
                    result.put("flag", false);
                    result.put("msg", "此账号已停用!");
                }else{
                    request.getSession().setAttribute(CommonConstants.USER_SESSION_KEY, admin);
                    result.put("flag", true);
                }
            }else {
                result.put("flag", false);
                result.put("msg", "密码错误!");
            }
        } else {
            result.put("flag", false);
            result.put("msg", "用户名不存在!");
        }
        return result;
    }

    public User getAccountByName(String account) {
        return userDao.getUserByName(account);
    }

    /**
     * 获取用户pagelist
     * @param params
     * @return
     */
    @Override
    public List<User> getUserPageList(Map<String, Object> params) {
        return userDao.getUserPageList(params);
    }

    /**
     * 获取用户总数量
     * @param params
     * @return
     */
    @Override
    public int getUserTotalCount(Map<String, Object> params) {
        return userDao.getUserTotalCount(params);
    }

    @Override
    public List<User> checkAccountName(Map<String, Object> param) {
        return userDao.checkAccountName(param);
    }

}
