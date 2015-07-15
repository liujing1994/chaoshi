package com.good.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.good.commons.DateUtil;
import com.good.dao.goodDao;
import com.good.dao.goodInRecordDao;
import com.good.dao.InRecordDao;
import com.good.service.InRecordService;
import com.good.vo.good;
import com.good.vo.goodInRecord;
import com.good.vo.InRecord;

/**
 * ����¼ҵ��ʵ����
 * 

 */
public class InRecordServiceImpl implements InRecordService {

	private InRecordDao inRecordDao;
	
	private goodInRecordDao goodInRecordDao;
	
	private goodDao goodDao;
	
	public InRecordServiceImpl(InRecordDao inRecordDao, goodInRecordDao goodInRecordDao, 
			goodDao goodDao) {
		this.inRecordDao = inRecordDao;
		this.goodInRecordDao = goodInRecordDao;
		this.goodDao = goodDao;
	}
	
	 
	public Collection<InRecord> getAll(Date date) {
		//�õ���һ��
		Date nextDate = DateUtil.getNextDate(date);
		//�õ����������, ��ʽΪyyyy-MM-dd
		String today = DateUtil.getDateString(date);
		//�õ����������, ��ʽΪyyyy-MM-dd
		String tomorrow = DateUtil.getDateString(nextDate);
		Collection<InRecord> records = inRecordDao.findByDate(today, tomorrow);
		for (InRecord r : records) {
			processData(r);
		}
		return records;
	}
	
	private InRecord processData(InRecord r) {
		Collection<goodInRecord> birs = goodInRecordDao.findByInRecord(r.getID());
		//���ü�¼�е�ÿһ����Ʒ
		setgood(birs);
		//��������¼�е���Ʒ������¼
		r.setgoodInRecords((Vector<goodInRecord>)birs);
		//������Ʒ��
		r.setgoodNames(getgoodNames(birs));
		//������Ʒ����
		r.setAmount(getAmount(birs));
		return r;
	}
	
	//��ȡһ������¼��������Ʒ�Ľ�����
	private int getAmount(Collection<goodInRecord> birs) {
		int result = 0;
		for (goodInRecord br : birs) {
			result += Integer.valueOf(br.getIN_SUM());
		}
		return result;
	}
	
	//���ò����е�ÿһ��goodInRecord��good����
	private void setgood(Collection<goodInRecord> birs) {
		for (goodInRecord bir : birs) {
			good good = goodDao.find(bir.getgood_ID_FK());
			bir.setgood(good);
		}
	}
	
	//��ȡһ������¼��������Ʒ������, �Զ��Ÿ���
	private String getgoodNames(Collection<goodInRecord> birs) {
		if (birs.size() == 0) return ""; 
		StringBuffer result = new StringBuffer();
		for (goodInRecord br : birs) {
			good good = br.getgood();
			result.append(good.getgood_NAME() + ", ");
		}
		//ȥ�����Ķ��Ų�����
		return result.substring(0, result.lastIndexOf(","));
	}

	 
	public InRecord get(String id) {
		InRecord r = inRecordDao.findById(id);
		return processData(r);
	}
 
	public void save(InRecord r) {
		String id = inRecordDao.save(r);
		for (goodInRecord br : r.getgoodInRecords()) {
			br.setT_IN_RECORD_ID_FK(id);
			goodInRecordDao.save(br);
			//�޸���Ʒ�Ŀ��
			String goodId = br.getgood().getID();
			good b = goodDao.find(goodId);
			b.setREPERTORY_SIZE(String.valueOf(Integer.valueOf(b.getREPERTORY_SIZE()) + Integer.valueOf(br.getIN_SUM())));
			goodDao.updateRepertory(b);
		}
	}

}
