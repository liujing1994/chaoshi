package com.good.service;

import java.util.Collection;

import com.good.vo.good;

/**
 * ��Ʒҵ��ӿ�
 
 */
public interface goodService {

	/**
	 * ����ȫ������Ʒ
	 * @return
	 */
	Collection<good> getAll();
	
	/**
	 * ����id��ȡ��Ʒ
	 * @param id
	 * @return
	 */
	good get(String id);
	
	/**
	 * ����һ����Ʒ
	 * @param good
	 * @return
	 */
	good add(good good);
	
	/**
	 * �޸�һ����Ʒ
	 * @param good
	 * @return
	 */
	good update(good good);
	
	/**
	 * ��������ģ����ѯ
	 * @param name
	 * @return
	 */
	Collection<good> find(String name);
}
