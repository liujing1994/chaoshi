package com.good.dao;

import com.good.vo.User;

/**
 * 用户DAO接口
 * 

 */
public interface UserDao {

	User getUser(String name, String password);
}
