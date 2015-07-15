package com.good.service;

import java.util.Collection;
import java.util.Date;

import com.good.vo.InRecord;

/**
 * ����¼ҵ��ӿ�
 */
public interface InRecordService {

	/**
	 * ����һ������¼
	 * @param r
	 */
	void save(InRecord r);
	
	/**
	 * �������ڲ��Ҷ�Ӧ������¼
	 * @param date
	 * @return
	 */
	Collection<InRecord> getAll(Date date);
	
	/**
	 * ����id�������¼
	 * @param id
	 * @return
	 */
	InRecord get(String id);
}
