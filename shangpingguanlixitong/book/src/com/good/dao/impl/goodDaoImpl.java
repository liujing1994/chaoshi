package com.good.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.good.dao.goodDao;
import com.good.vo.good;

/**
 * 商品DAO实现类

 */
public class goodDaoImpl extends CommonDaoImpl implements goodDao {

	public good find(String id) {
		String sql = "SELECT * FROM T_good good WHERE good.ID='" + id + "'";
		List<good> datas =  (List<good>)getDatas(sql, new ArrayList(), good.class);
		return datas.get(0);
	}

 
	public Collection<good> findAll() {
		String sql = "SELECT * FROM T_good good ORDER BY good.ID desc";
		return getDatas(sql, new Vector(), good.class);
	}
 
	public String add(good good) {
		//根据good对象拼装SQL
		String sql = "INSERT INTO T_good VALUES (ID, '" + good.getgood_NAME() + "', '" + 
		good.getgood_INTRO() + "', '" + good.getgood_PRICE() + "', '" + good.getTYPE_ID_FK() + 
		"', '" + good.getPUB_ID_FK() + "', '" + good.getIMAGE_URL() + 
		"', '" + good.getAUTHOR() + "', '" + good.getREPERTORY_SIZE() + "')";
		//执行SQL并返回ID
		return String.valueOf(getJDBCExecutor().executeUpdate(sql));
	}

	 
	public String update(good good) {
		String sql = "UPDATE T_good good SET good.good_NAME='" + good.getgood_NAME() + 
		"', good.good_INTRO='" + good.getgood_INTRO() + "', good.good_PRICE='" + 
		good.getgood_PRICE() + "', good.TYPE_ID_FK='" + good.getTYPE_ID_FK() + 
		"', good.PUB_ID_FK='" + good.getPUB_ID_FK() + "', good.IMAGE_URL='" + 
		good.getIMAGE_URL() + "', good.AUTHOR='" + good.getAUTHOR() + 
		"' WHERE good.ID='" + good.getID() + "'";
		getJDBCExecutor().executeUpdate(sql);
		return good.getID();
	}
 
	public Collection<good> findByName(String name) {
		String sql = "SELECT * FROM T_good good WHERE good.good_NAME like '%" + name 
		+ "%'" + " ORDER BY good.ID DESC";
		return getDatas(sql, new Vector(), good.class);
	}

	 
	public void updateRepertory(good b) {
		String sql = "UPDATE T_good good SET good.REPERTORY_SIZE='" + b.getREPERTORY_SIZE() + 
		"' WHERE good.ID='" + b.getID() + "'";
		getJDBCExecutor().executeUpdate(sql);
	}
	
	

}
