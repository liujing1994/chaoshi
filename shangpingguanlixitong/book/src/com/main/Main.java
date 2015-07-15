package com.main;

import com.good.dao.UserDao;
import com.good.dao.impl.UserDaoImpl;
import com.good.service.UserService;
import com.good.service.impl.UserServiceImpl;
import com.good.ui.LoginFrame;

/**
 * 程序入口类

 */
public class Main {


	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		UserService userService = new UserServiceImpl(userDao);
		LoginFrame loginFrame = new LoginFrame(userService);
	}

}
