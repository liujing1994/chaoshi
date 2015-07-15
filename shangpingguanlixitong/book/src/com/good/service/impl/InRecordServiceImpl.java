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
 * 入库记录业务实现类
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
		//得到下一天
		Date nextDate = DateUtil.getNextDate(date);
		//得到今天的日期, 格式为yyyy-MM-dd
		String today = DateUtil.getDateString(date);
		//得到明天的日期, 格式为yyyy-MM-dd
		String tomorrow = DateUtil.getDateString(nextDate);
		Collection<InRecord> records = inRecordDao.findByDate(today, tomorrow);
		for (InRecord r : records) {
			processData(r);
		}
		return records;
	}
	
	private InRecord processData(InRecord r) {
		Collection<goodInRecord> birs = goodInRecordDao.findByInRecord(r.getID());
		//设置记录中的每一本商品
		setgood(birs);
		//设置入库记录中的商品的入库记录
		r.setgoodInRecords((Vector<goodInRecord>)birs);
		//设置商品名
		r.setgoodNames(getgoodNames(birs));
		//设置商品总数
		r.setAmount(getAmount(birs));
		return r;
	}
	
	//获取一次入库记录中所有商品的交易量
	private int getAmount(Collection<goodInRecord> birs) {
		int result = 0;
		for (goodInRecord br : birs) {
			result += Integer.valueOf(br.getIN_SUM());
		}
		return result;
	}
	
	//设置参数中的每一个goodInRecord的good属性
	private void setgood(Collection<goodInRecord> birs) {
		for (goodInRecord bir : birs) {
			good good = goodDao.find(bir.getgood_ID_FK());
			bir.setgood(good);
		}
	}
	
	//获取一次入库记录中所有商品的名字, 以逗号隔开
	private String getgoodNames(Collection<goodInRecord> birs) {
		if (birs.size() == 0) return ""; 
		StringBuffer result = new StringBuffer();
		for (goodInRecord br : birs) {
			good good = br.getgood();
			result.append(good.getgood_NAME() + ", ");
		}
		//去掉最后的逗号并返回
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
			//修改商品的库存
			String goodId = br.getgood().getID();
			good b = goodDao.find(goodId);
			b.setREPERTORY_SIZE(String.valueOf(Integer.valueOf(b.getREPERTORY_SIZE()) + Integer.valueOf(br.getIN_SUM())));
			goodDao.updateRepertory(b);
		}
	}

}
