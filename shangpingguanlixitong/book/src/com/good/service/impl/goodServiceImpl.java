package com.good.service.impl;

import java.util.Collection;

import com.good.dao.goodDao;
import com.good.dao.ConcernDao;
import com.good.dao.TypeDao;
import com.good.service.goodService;
import com.good.vo.good;
import com.good.vo.Concern;
import com.good.vo.Type;

/**
 * 商品业务实现类
 
 */
public class goodServiceImpl implements goodService {

	private goodDao goodDao;
	
	private TypeDao typeDao;
	
	private ConcernDao concernDao;
	
	public goodServiceImpl(goodDao goodDao, TypeDao typeDao, ConcernDao concernDao) {
		this.goodDao = goodDao;
		this.typeDao = typeDao;
		this.concernDao = concernDao;
	}
	
 
	public good get(String id) {
		good good = goodDao.find(id);
		//查找商品对应的种类
		Type type = typeDao.find(good.getTYPE_ID_FK());
		//查找商品的商家
		Concern concern = concernDao.find(good.getPUB_ID_FK());
		good.setType(type);
		good.setConcern(concern);
		return good;
	}

	 
	public Collection<good> getAll() {
		Collection<good> result = goodDao.findAll();
		//调用setAssociate方法设置关联的两个对象
		return setAssociate(result);
	}
	
	//设置关系对象
	private Collection<good> setAssociate(Collection<good> result) {
		//遍历结果集合，设置每一个商品的对象
		for (good good : result) {
			//查找出对应的种类，再为商品设置种类对象
			good.setType(typeDao.find(good.getTYPE_ID_FK()));
			//查找出对应的商家，再为商品设置商家对象
			good.setConcern(concernDao.find(good.getPUB_ID_FK()));
		}
		return result;
	}

	 
	public good add(good good) {
		String id = goodDao.add(good);
		return get(id);
	}

	 
	public good update(good good) {
		String id = goodDao.update(good);
		return get(id);
	}

	 
	public Collection<good> find(String name) {
		Collection<good> result = goodDao.findByName(name);
		return setAssociate(result);
	}
}
