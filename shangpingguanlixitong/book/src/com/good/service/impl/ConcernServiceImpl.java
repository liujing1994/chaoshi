package com.good.service.impl;

import java.util.Collection;

import com.good.dao.ConcernDao;
import com.good.service.ConcernService;
import com.good.vo.Concern;

/**
 * �̼�ҵ��ʵ����
 * 

 */
public class ConcernServiceImpl implements ConcernService {

	private ConcernDao dao;
	
	public ConcernServiceImpl(ConcernDao dao) {
		this.dao = dao;
	}
 
	public Collection<Concern> getAll() {
		return dao.findAll();
	}
 
	public Concern find(String id) {
		return dao.find(id);
	}

	 
	public Concern add(Concern c) {
		String id = dao.add(c);
		return find(id);
	}
 
	public Concern update(Concern c) {
		//����DAO�����޸Ķ���
		String id = dao.update(c);
		//���²��Ҹö���
		return find(id);
	}
 
	public Collection<Concern> query(String name) {
		return dao.findByName(name);
	}

}
