package com.good.dao;

import java.util.Collection;

import com.good.vo.Concern;

/**
 * �̼�DAO�ӿ�
 * 

 */
public interface ConcernDao {

	/**
	 * ����ȫ�����̼�
	 * @return
	 */
	Collection<Concern> findAll();
	
	/**
	 * ����ID�����̼�
	 * @param id
	 * @return
	 */
	Concern find(String id);
	
	/**
	 * ���һ���̼�
	 * @param concern 
	 * @return
	 */
	String add(Concern concern);
	
	/**
	 * �޸�һ���̼�
	 * @param concern
	 * @return
	 */
	String update(Concern concern);
	
	/**
	 * ��������ģ�������̼�
	 * @param name
	 * @return
	 */
	Collection<Concern> findByName(String name);
}
