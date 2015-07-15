package com.good.service.impl;

import java.util.Collection;

import com.good.dao.ConcernDao;
import com.good.service.ConcernService;
import com.good.vo.Concern;

/**
 * 商家业务实现类
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
		//调用DAO方法修改对象
		String id = dao.update(c);
		//重新查找该对象
		return find(id);
	}
 
	public Collection<Concern> query(String name) {
		return dao.findByName(name);
	}

}
