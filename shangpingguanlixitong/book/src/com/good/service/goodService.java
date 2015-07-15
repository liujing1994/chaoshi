package com.good.service;

import java.util.Collection;

import com.good.vo.good;

/**
 * 商品业务接口
 
 */
public interface goodService {

	/**
	 * 查找全部的商品
	 * @return
	 */
	Collection<good> getAll();
	
	/**
	 * 根据id获取商品
	 * @param id
	 * @return
	 */
	good get(String id);
	
	/**
	 * 新增一本商品
	 * @param good
	 * @return
	 */
	good add(good good);
	
	/**
	 * 修改一本商品
	 * @param good
	 * @return
	 */
	good update(good good);
	
	/**
	 * 根据名称模糊查询
	 * @param name
	 * @return
	 */
	Collection<good> find(String name);
}
