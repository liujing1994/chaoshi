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
 * 销售记录业务实现类
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
 
	//实现接口方法
	public Collection<SaleRecord> getAll(Date date) {
		//得到下一天
		Date nextDate = DateUtil.getNextDate(date);
		//得到今天的日期, 格式为yyyy-MM-dd
		String today = DateUtil.getDateString(date);
		//得到明天的日期, 格式为yyyy-MM-dd
		String tomorrow = DateUtil.getDateString(nextDate);
		Collection<SaleRecord> records = saleRecordDao.findByDate(today, tomorrow);
		for (SaleRecord r : records) {
			processDatas(r);
		}
		return records;
	}
	
	//处理一个SaleRecord, 设置它的商品销售记录属性和商品名字属性
	private SaleRecord processDatas(SaleRecord r) {
		//查找该记录所对应的商品的销售记录
		Collection<goodSaleRecord> brs = goodSaleRecordDao.findBySaleRecord(r.getID());
		//设置结果集中的每一个good属性
		setgood(brs);
		//设置SaleRecord对象中的商品的销售记录集合
		r.setgoodSaleRecords((Vector<goodSaleRecord>)brs);
		//设置SaleRecord的商品名集合
		r.setgoodNames(getgoodNames(brs));
		//设置数量与总价
		r.setAmount(getAmount(brs));
		r.setTotalPrice(getTotalPrice(brs));
		return r;
	}
	
	//获取一次交易中涉及的总价
	private double getTotalPrice(Collection<goodSaleRecord> brs) {
		double result = 0;
		for (goodSaleRecord br : brs) {
			//商品的交易量
			int tradeSum = Integer.valueOf(br.getTRADE_SUM());
			//商品的单价
			double goodPrice = Double.valueOf(br.getgood().getgood_PRICE());
			result += (goodPrice * tradeSum);
		}
		return result;
	}
	
	//获取一次交易中所有商品的交易量
	private int getAmount(Collection<goodSaleRecord> brs) {
		int result = 0;
		//遍历商品的交易记录，计算总价
		for (goodSaleRecord br : brs) {
			result += Integer.valueOf(br.getTRADE_SUM());
		}
		return result;
	}
	
	//设置参数中的每一个goodSaleRecord的good属性
	private void setgood(Collection<goodSaleRecord> brs) {
		for (goodSaleRecord br : brs) {
			//调商品的数据访问层接口
			good good = goodDao.find(br.getgood_ID_FK());
			br.setgood(good);
		}
	}
	
	//获取一次交易中所有商品的名字, 以逗号隔开
	private String getgoodNames(Collection<goodSaleRecord> brs) {
		if (brs.size() == 0) return ""; 
		StringBuffer result = new StringBuffer();
		for (goodSaleRecord br : brs) {
			good good = br.getgood();
			result.append(good.getgood_NAME() + ", ");
		}
		//去掉最后的逗号并返回
		return result.substring(0, result.lastIndexOf(","));
	}
	

	 
	public void saveRecord(SaleRecord record) {
		//遍历判断商品的库存是否不够
		for (goodSaleRecord r : record.getgoodSaleRecords()) {
			String goodId = r.getgood().getID();
			good b = goodDao.find(goodId);
			//当存库不够时,抛出异常
			if (Integer.valueOf(r.getTRADE_SUM()) > 
				Integer.valueOf(b.getREPERTORY_SIZE())) {
				throw new BusinessException(b.getgood_NAME() + " 的库存不够");
			}
		}
		//先保存交易记录
		String id = saleRecordDao.save(record);
		//再保存商品的交易记录
		for (goodSaleRecord r : record.getgoodSaleRecords()) {
			//设置销售记录id
			r.setT_SALE_RECORD_ID_FK(id);
			goodSaleRecordDao.savegoodSaleRecord(r);
			//修改商品的库存
			String goodId = r.getgood().getID();
			good b = goodDao.find(goodId);
			//计算剩余的库存
			int leave = Integer.valueOf(b.getREPERTORY_SIZE()) - 
				Integer.valueOf(r.getTRADE_SUM());
			//设置库存并将库存数保存到数据库
			b.setREPERTORY_SIZE(String.valueOf(leave));
			goodDao.updateRepertory(b);
		}
	}

}
