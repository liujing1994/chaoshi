package com.good.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.good.commons.BusinessException;
import com.good.commons.DateUtil;
import com.good.dao.goodDao;
import com.good.dao.goodSaleRecordDao;
import com.good.dao.SaleRecordDao;
import com.good.service.SaleRecordService;
import com.good.vo.good;
import com.good.vo.goodSaleRecord;
import com.good.vo.SaleRecord;

/**
 * ���ۼ�¼ҵ��ʵ����
 * 

 */
public class SaleRecordServiceImpl implements SaleRecordService {
	
	private SaleRecordDao saleRecordDao;
	
	private goodSaleRecordDao goodSaleRecordDao;
	
	private goodDao goodDao;
	
	public SaleRecordServiceImpl(SaleRecordDao saleRecordDao, 
			goodSaleRecordDao goodSaleRecordDao, goodDao goodDao) {
		this.saleRecordDao = saleRecordDao;
		this.goodSaleRecordDao = goodSaleRecordDao;
		this.goodDao = goodDao;
	}
	
	 
	public SaleRecord get(String id) {
		SaleRecord r = saleRecordDao.findById(id);
		return processDatas(r);
	}
 
	//ʵ�ֽӿڷ���
	public Collection<SaleRecord> getAll(Date date) {
		//�õ���һ��
		Date nextDate = DateUtil.getNextDate(date);
		//�õ����������, ��ʽΪyyyy-MM-dd
		String today = DateUtil.getDateString(date);
		//�õ����������, ��ʽΪyyyy-MM-dd
		String tomorrow = DateUtil.getDateString(nextDate);
		Collection<SaleRecord> records = saleRecordDao.findByDate(today, tomorrow);
		for (SaleRecord r : records) {
			processDatas(r);
		}
		return records;
	}
	
	//����һ��SaleRecord, ����������Ʒ���ۼ�¼���Ժ���Ʒ��������
	private SaleRecord processDatas(SaleRecord r) {
		//���Ҹü�¼����Ӧ����Ʒ�����ۼ�¼
		Collection<goodSaleRecord> brs = goodSaleRecordDao.findBySaleRecord(r.getID());
		//���ý�����е�ÿһ��good����
		setgood(brs);
		//����SaleRecord�����е���Ʒ�����ۼ�¼����
		r.setgoodSaleRecords((Vector<goodSaleRecord>)brs);
		//����SaleRecord����Ʒ������
		r.setgoodNames(getgoodNames(brs));
		//�����������ܼ�
		r.setAmount(getAmount(brs));
		r.setTotalPrice(getTotalPrice(brs));
		return r;
	}
	
	//��ȡһ�ν������漰���ܼ�
	private double getTotalPrice(Collection<goodSaleRecord> brs) {
		double result = 0;
		for (goodSaleRecord br : brs) {
			//��Ʒ�Ľ�����
			int tradeSum = Integer.valueOf(br.getTRADE_SUM());
			//��Ʒ�ĵ���
			double goodPrice = Double.valueOf(br.getgood().getgood_PRICE());
			result += (goodPrice * tradeSum);
		}
		return result;
	}
	
	//��ȡһ�ν�����������Ʒ�Ľ�����
	private int getAmount(Collection<goodSaleRecord> brs) {
		int result = 0;
		//������Ʒ�Ľ��׼�¼�������ܼ�
		for (goodSaleRecord br : brs) {
			result += Integer.valueOf(br.getTRADE_SUM());
		}
		return result;
	}
	
	//���ò����е�ÿһ��goodSaleRecord��good����
	private void setgood(Collection<goodSaleRecord> brs) {
		for (goodSaleRecord br : brs) {
			//����Ʒ�����ݷ��ʲ�ӿ�
			good good = goodDao.find(br.getgood_ID_FK());
			br.setgood(good);
		}
	}
	
	//��ȡһ�ν�����������Ʒ������, �Զ��Ÿ���
	private String getgoodNames(Collection<goodSaleRecord> brs) {
		if (brs.size() == 0) return ""; 
		StringBuffer result = new StringBuffer();
		for (goodSaleRecord br : brs) {
			good good = br.getgood();
			result.append(good.getgood_NAME() + ", ");
		}
		//ȥ�����Ķ��Ų�����
		return result.substring(0, result.lastIndexOf(","));
	}
	

	 
	public void saveRecord(SaleRecord record) {
		//�����ж���Ʒ�Ŀ���Ƿ񲻹�
		for (goodSaleRecord r : record.getgoodSaleRecords()) {
			String goodId = r.getgood().getID();
			good b = goodDao.find(goodId);
			//����ⲻ��ʱ,�׳��쳣
			if (Integer.valueOf(r.getTRADE_SUM()) > 
				Integer.valueOf(b.getREPERTORY_SIZE())) {
				throw new BusinessException(b.getgood_NAME() + " �Ŀ�治��");
			}
		}
		//�ȱ��潻�׼�¼
		String id = saleRecordDao.save(record);
		//�ٱ�����Ʒ�Ľ��׼�¼
		for (goodSaleRecord r : record.getgoodSaleRecords()) {
			//�������ۼ�¼id
			r.setT_SALE_RECORD_ID_FK(id);
			goodSaleRecordDao.savegoodSaleRecord(r);
			//�޸���Ʒ�Ŀ��
			String goodId = r.getgood().getID();
			good b = goodDao.find(goodId);
			//����ʣ��Ŀ��
			int leave = Integer.valueOf(b.getREPERTORY_SIZE()) - 
				Integer.valueOf(r.getTRADE_SUM());
			//���ÿ�沢����������浽���ݿ�
			b.setREPERTORY_SIZE(String.valueOf(leave));
			goodDao.updateRepertory(b);
		}
	}

}
