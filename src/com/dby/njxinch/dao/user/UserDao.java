package com.dby.njxinch.dao.user;

import com.dby.njxinch.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
	User getUserByName(String account);

	/**
	 * 获取用户pagelist
	 * @param params
	 * @return
	 */
	List<User> getUserPageList(Map<String, Object> params);

	/**
	 * 获取用户总数量
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
