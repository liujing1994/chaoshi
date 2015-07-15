package com.good.service;

import java.util.Collection;

import com.good.vo.Concern;

/**
 * �̼�ҵ��ӿ�
 */
public interface ConcernService {

	/**
	 * ��ȡȫ�����̼�
	 * @return
	 */
	Collection<Concern> getAll();
	
	/**
	 * ����id����һ���̼�
	 * @param id �̼�id
	 * @return
	 */
	Concern find(String id);
	
	/**
	 * ���һ���̼�
	 * @param c
	 * @return
	 */
	Concern add(Concern c);
	
	/**
	 * �޸�һ���̼�
	 * @param c
	 * @return
	 */
	Concern update(Concern c);
	
	/**
	 * �����̼�����ģ������
	 * @param name
	 * @return
	 */
	Collection<Concern> query(String name);
}
