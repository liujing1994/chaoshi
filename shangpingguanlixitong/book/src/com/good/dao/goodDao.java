package com.good.dao;

import java.util.Collection;

import com.good.vo.good;

/**
 * ��ƷDAO�ӿ�
 * 

 */
public interface goodDao {

	/**
	 * ����ȫ������Ʒ
	 * @return
	 */
	Collection<good> findAll();
	
	/**
	 * ������ƷID��ȡ��Ʒ
	 * @param id
	 * @return
	 */
	good find(String id);
	
	/**
	 * ���һ����Ʒ, ��������Ӻ���Ʒ��id
	 * @param good
	 * @return
	 */
	String add(good good);
	
	/**
	 * �޸�һ����Ʒ, ������Ʒ��id
	 * @param good
	 * @return
	 */
	String update(good good);
	
	/**
	 * ������Ʒ����ģ��������Ʒ
	 * @param name
	 * @return
	 */
	Collection<good> findByName(String name);
	
	/**
	 * �޸���Ʒ�Ŀ��
	 * @param b
	 */
	void updateRepertory(good b);
	
}
