package com.good.service;

import java.util.Collection;
import java.util.List;

import com.good.vo.Type;

/**
 * ��Ʒ����ҵ��ӿ�
 * 
 
 */
public interface TypeService {

	/**
	 * �������е�����
	 * @return ��������ֵ���󼯺�
	 */
	Collection<Type> getAll();
	
	/**
	 * ������������ģ����������
	 * @param name ��������
	 * @return ���ҵĽ����
	 */
	Collection<Type> query(String name);
	
	/**
	 * ����һ����Ʒ����
	 * @param type ��Ҫ�����Ķ���
	 * @return ��������������
	 */
	Type add(Type type);
	
	/**
	 * �޸�һ����Ʒ����
	 * @param type ��Ҫ�޸ĵĶ���
	 * @return �޸ĺ�Ķ���
	 */
	Type update(Type type);
	
	/**
	 * ������������һ������
	 * @param id
	 * @return
	 */
	Type get(String id);
}
