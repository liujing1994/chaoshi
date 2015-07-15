package com.good.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.good.dao.ConcernDao;
import com.good.vo.Concern;

/**
 * �̼�DAOʵ����

 */
public class ConcernDaoImpl extends CommonDaoImpl implements ConcernDao {
 
	public String add(Concern c) {
		//�����̼Ҷ���ƴװSQL
		String sql = "INSERT INTO t_supplierr VALUES (ID, '" + 
		c.getPUB_NAME() + "', '" + c.getPUB_TEL() + "', '" + c.getPUB_LINK_MAN() + 
		"', '" + c.getPUB_INTRO() + "')";
		//����JDBCExecutor��executeUpdate�����������������ݵ�����
		String id = String.valueOf(getJDBCExecutor().executeUpdate(sql));
		return id;
	}

	 
	public Concern find(String id) {
		String sql = "SELECT * FROM t_supplierr pub WHERE pub.ID = '" + id + "'";
		List<Concern> datas = (List<Concern>)getDatas(sql, new ArrayList(), Concern.class);
		return (datas.size() == 1) ? datas.get(0) : null;
	}

	 
	public Collection<Concern> findAll() {
		String sql = "SELECT * FROM t_supplierr pub ORDER BY pub.ID DESC";
		return getDatas(sql, new Vector(), Concern.class);
	}

 
	public String update(Concern c) {
		String sql = "UPDATE t_supplierr pub SET pub.PUB_NAME='" + c.getPUB_NAME() + 
		"', pub.PUB_TEL='" + c.getPUB_TEL() + "', pub.PUB_LINK_MAN='" + c.getPUB_LINK_MAN() + 
		"', pub.PUB_INTRO='" + c.getPUB_INTRO() + "' WHERE pub.ID='" + c.getID() + "'";
		getJDBCExecutor().executeUpdate(sql);
		return c.getID();
	}
 
	public Collection<Concern> findByName(String name) {
		String sql = "SELECT * FROM t_supplierr pub WHERE pub.PUB_NAME like '%" + 
		name + "%' ORDER BY pub.ID DESC";
		return getDatas(sql, new Vector(), Concern.class);
	}

	
}
