package com.good.dao.impl;

import java.sql.ResultSet;
import java.util.Collection;

import com.good.commons.DataUtil;
import com.good.vo.ValueObject;

import com.base.JDBCExecutor;

/**
 * ����DAO�Ļ���
 * 

 */
public class CommonDaoImpl {
	//����JDBCExecutor����
	public JDBCExecutor getJDBCExecutor() {
		return JDBCExecutor.getJDBCExecutor();
	}
	
	//���ݲ�����SQL, ��Ž���ļ��϶���, �;�������ݿ�ӳ����󷵻�һ������
	public Collection getDatas(String sql, Collection<ValueObject> result, 
			Class clazz) {
		//ִ��SQL����ResultSet����
		ResultSet rs = getJDBCExecutor().executeQuery(sql);
		//��ResultSet���з�װ�����ؼ���
		return DataUtil.getDatas(result, rs, clazz);
	}
}
