package com.good.vo;

import java.util.Vector;

/**
 * 入库记录
 * 

 */
public class InRecord extends ValueObject {

	//入库日期
	private String RECORD_DATE;
	
	//入库的总数量
	private int amount;
	
	//该入库记录所对应的商品的入库记录
	private Vector<goodInRecord> goodInRecords;
	
	//入库商品的名称, 以逗号隔开
	private String goodNames;

	public String getRECORD_DATE() {
		return RECORD_DATE;
	}

	public void setRECORD_DATE(String record_date) {
		RECORD_DATE = record_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Vector<goodInRecord> getgoodInRecords() {
		return goodInRecords;
	}

	public void setgoodInRecords(Vector<goodInRecord> goodInRecords) {
		this.goodInRecords = goodInRecords;
	}

	public String getgoodNames() {
		return goodNames;
	}

	public void setgoodNames(String goodNames) {
		this.goodNames = goodNames;
	}
	
	
}
