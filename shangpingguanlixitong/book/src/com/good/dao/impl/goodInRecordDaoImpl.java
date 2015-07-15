package com.good.dao.impl;

import java.util.Collection;
import java.util.Vector;

import com.good.dao.goodInRecordDao;
import com.good.vo.goodInRecord;

/**
 * 商品入库DAO实现类
 * 

 */
public class goodInRecordDaoImpl extends CommonDaoImpl implements
		goodInRecordDao {

	 
	public Collection<goodInRecord> findByInRecord(String inRecordId) {
		String sql = "SELECT * FROM T_good_IN_RECORD r WHERE r.T_IN_RECORD_ID_FK='" + 
		inRecordId + "'";
		return getDatas(sql, new Vector(), goodInRecord.class);
	}

	 
	public String save(goodInRecord r) {
		String sql = "INSERT INTO T_good_IN_RECORD VALUES (ID, '" + r.getgood().getID() + 
		"', '" + r.getT_IN_RECORD_ID_FK() + "', '" + r.getIN_SUM() + "')";
		return String.valueOf(getJDBCExecutor().executeUpdate(sql));

	}

}
