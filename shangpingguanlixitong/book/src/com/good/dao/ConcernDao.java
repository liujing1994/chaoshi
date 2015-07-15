package com.good.dao;

import java.util.Collection;

import com.good.vo.Concern;

/**
 * 商家DAO接口
 * 

 */
public interface ConcernDao {

	/**
	 * 查找全部的商家
	 * @return
	 */
	Collection<Concern> findAll();
	
	/**
	 * 根据ID查找商家
	 * @param id
	 * @return
	 */
	Concern find(String id);
	
	/**
	 * 添加一个商家
	 * @param concern 
	 * @return
	 */
	String add(Concern concern);
	
	/**
	 * 修改一个商家
	 * @param concern
	 * @return
	 */
	String update(Concern concern);
	
	/**
	 * 根据名字模糊查找商家
	 * @param name
	 * @return
	 */
	Collection<Concern> findByName(String name);
}
