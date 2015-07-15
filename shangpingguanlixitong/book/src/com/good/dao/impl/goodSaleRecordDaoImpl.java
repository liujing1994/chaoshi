package com.good.dao.impl;

import java.util.Collection;
import java.util.Vector;

import com.good.dao.goodSaleRecordDao;
import com.good.vo.goodSaleRecord;

/**
 * 商品销售记录DAO实现类
 * 

 */
public class goodSaleRecordDaoImpl extends CommonDaoImpl implements
		goodSaleRecordDao {
 
	//根据销售记录id获得商品的销售记录集合
	public Collection<goodSaleRecord> findBySaleRecord(String saleRecordId) {
		String sql = "SELECT * FROM T_good_SALE_RECORD r WHERE r.T_SALE_RECORD_ID_FK='" + 
		saleRecordId + "'";
		return getDatas(sql, new Vector(), goodSaleRecord.class);
	}

	 
	public String savegoodSaleRecord(goodSaleRecord record) {
		String sql = "INSERT INTO T_good_SALE_RECORD VALUES (ID, '" + record.getgood().getID() + 
		"', '" + record.getT_SALE_RECORD_ID_FK() + "', '" + record.getTRADE_SUM() + "')";
		return String.valueOf(getJDBCExecutor().executeUpdate(sql));
	}

}
