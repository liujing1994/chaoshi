package com.good.service.impl;

import java.util.Collection;

import com.good.dao.TypeDao;
import com.good.dao.impl.TypeDaoImpl;
import com.good.service.TypeService;
import com.good.vo.Type;

/**
 * 商品种类业务实现类
 
 */
public class TypeServiceImpl implements TypeService {

	private TypeDao dao;
	
	public TypeServiceImpl(TypeDao dao) {
		this.dao = dao;
	}
	
	public Collection<Type> query(String name) {
		return dao.findByName(name);
	}

	public Collection<Type> getAll() {
		return dao.find();
	}

	public Type add(Type type) {
		String id = dao.add(type);
		return get(id);
	}

	public Type update(Type type) {
		// TODO Auto-generated method stub
		String id = dao.update(type);
		return get(id);
	}
	
	public Type get(String id) {
		return dao.find(id);
	}

}
