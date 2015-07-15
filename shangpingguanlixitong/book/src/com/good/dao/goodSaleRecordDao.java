package com.good.dao;

import java.util.Collection;

import com.good.vo.goodSaleRecord;

/**
 * ��Ʒ���ۼ�¼DAO�ӿ�
 * 

 */
public interface goodSaleRecordDao {

	/**
	 * �������ۼ�¼id��ȡ�����ۼ�¼�����е���Ʒ�����ۼ�¼
	 * @param saleRecordId
	 * @return
	 */
	Collection<goodSaleRecord> findBySaleRecord(String saleRecordId);

	/**
	 * ����һ����Ʒ�����ۼ�¼
	 * @param record
	 * @return
	 */
	String savegoodSaleRecord(goodSaleRecord record);
	
}
