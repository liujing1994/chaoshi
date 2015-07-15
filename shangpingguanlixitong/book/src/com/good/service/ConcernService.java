package com.good.service;

import java.util.Collection;

import com.good.vo.Concern;

/**
 * 商家业务接口
 */
public interface ConcernService {

	/**
	 * 获取全部的商家
	 * @return
	 */
	Collection<Concern> getAll();
	
	/**
	 * 根据id查找一个商家
	 * @param id 商家id
	 * @return
	 */
	Concern find(String id);
	
	/**
	 * 添加一个商家
	 * @param c
	 * @return
	 */
	Concern add(Concern c);
	
	/**
	 * 修改一个商家
	 * @param c
	 * @return
	 */
	Concern update(Concern c);
	
	/**
	 * 根据商家名字模糊查找
	 * @param name
	 * @return
	 */
	Collection<Concern> query(String name);
}
