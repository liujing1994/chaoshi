package com.good.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.good.dao.UserDao;
import com.good.vo.User;

/**
 * 用户DAO实现类
  
 */
public class UserDaoImpl extends CommonDaoImpl implements UserDao {

	 
	public User getUser(String name, String password) {
		String sql = "SELECT * FROM T_USER user WHERE user.USER_NAME='" + name + "' and user.USER_PASSWORD='" + 
		password + "'";
		List<User> datas = (List<User>)getDatas(sql, new ArrayList(), User.class);
		return (datas.size() == 1) ? datas.get(0) : null;
	}

}
