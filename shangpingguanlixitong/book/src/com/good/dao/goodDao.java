package com.good.dao;

import java.util.Collection;

import com.good.vo.good;

/**
 * 商品DAO接口
 * 

 */
public interface goodDao {

	/**
	 * 查找全部的商品
	 * @return
	 */
	Collection<good> findAll();
	
	/**
	 * 根据商品ID获取商品
	 * @param id
	 * @return
	 */
	good find(String id);
	
	/**
	 * 添加一本商品, 并返回添加后商品的id
	 * @param good
	 * @return
	 */
	String add(good good);
	
	/**
	 * 修改一本商品, 返回商品的id
	 * @param good
	 * @return
	 */
	String update(good good);
	
	/**
	 * 根据商品名称模糊查找商品
	 * @param name
	 * @return
	 */
	Collection<good> findByName(String name);
	
	/**
	 * 修改商品的库存
	 * @param b
	 */
	void updateRepertory(good b);
	
}
