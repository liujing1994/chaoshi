package com.good.service;

/**
 * �û�ҵ��ӿ�
 
 */
public interface UserService {

	/**
	 * �û���¼�ķ���, �����¼ʧ�ܣ����׳�BusinessException
	 * @param name
	 * @param password
	 */
	void login(String name, String password);
}
