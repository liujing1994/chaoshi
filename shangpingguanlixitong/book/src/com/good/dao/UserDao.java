package com.good.dao;

import com.good.vo.User;

/**
 * �û�DAO�ӿ�
 * 

 */
public interface UserDao {

	User getUser(String name, String password);
}
