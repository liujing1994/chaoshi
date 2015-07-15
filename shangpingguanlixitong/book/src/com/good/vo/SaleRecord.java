package com.good.vo;

import java.util.List;
import java.util.Vector;

/**
 * 销售记录
 */
public class SaleRecord extends ValueObject {
	//交易日期
	private String RECORD_DATE;
	//销售的总数量
	private int amount;
	//总价钱
	private double totalPrice;
	//商品的销售记录
	private Vector<goodSaleRecord> goodSaleRecords;
	
	//该记录中对应所有商品的名称, 显示用
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Vector<goodSaleRecord> getgoodSaleRecords() {
		return goodSaleRecords;
	}

	public void setgoodSaleRecords(Vector<goodSaleRecord> goodSaleRecords) {
		this.goodSaleRecords = goodSaleRecords;
	}

	public String getgoodNames() {
		return goodNames;
	}

	public void setgoodNames(String goodNames) {
		this.goodNames = goodNames;
	}
	
	
}
