package com.good.dao;

import java.util.Collection;

import com.good.vo.goodInRecord;

/**
 * ��Ʒ����¼DAO�ӿ�
 * 

 */
public interface goodInRecordDao {

	/**
	 * ��������¼����ȫ������Ʒ������¼
	 * @param inRecordId
	 * @return
	 */
	Collection<goodInRecord> findByInRecord(String inRecordId);
	
	/**
	 * ����һ����Ʒ������¼, �����ظü�¼��id
	 * @param r
	 * @return
	 */
	String save(goodInRecord r);
}
