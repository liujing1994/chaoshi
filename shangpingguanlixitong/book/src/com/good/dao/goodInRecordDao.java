package com.good.dao;

import java.util.Collection;

import com.good.vo.goodInRecord;

/**
 * 商品入库记录DAO接口
 * 

 */
public interface goodInRecordDao {

	/**
	 * 根据入库记录查找全部的商品的入库记录
	 * @param inRecordId
	 * @return
	 */
	Collection<goodInRecord> findByInRecord(String inRecordId);
	
	/**
	 * 保存一条商品的入库记录, 并返回该记录的id
	 * @param r
	 * @return
	 */
	String save(goodInRecord r);
}
