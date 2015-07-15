package com.good.service.impl;

import com.good.commons.BusinessException;
import com.good.dao.UserDao;
import com.good.service.UserService;
import com.good.vo.User;

/**
 * 用户业务实现类

 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void login(String name, String password) {
		User user = userDao.getUser(name, password);
		if (user == null) {
			throw new BusinessException("用户名密码错误");
		}
	}

}
