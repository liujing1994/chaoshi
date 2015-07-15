package com.good.dao;

import java.util.Collection;

import com.good.vo.goodSaleRecord;

/**
 * 商品销售记录DAO接口
 * 

 */
public interface goodSaleRecordDao {

	/**
	 * 根据销售记录id获取该销售记录下所有的商品的销售记录
	 * @param saleRecordId
	 * @return
	 */
	Collection<goodSaleRecord> findBySaleRecord(String saleRecordId);

	/**
	 * 保存一条商品的销售记录
	 * @param record
	 * @return
	 */
	String savegoodSaleRecord(goodSaleRecord record);
	
}
