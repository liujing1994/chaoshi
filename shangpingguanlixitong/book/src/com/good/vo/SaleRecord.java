package com.good.vo;

import java.util.List;
import java.util.Vector;

/**
 * ���ۼ�¼
 */
public class SaleRecord extends ValueObject {
	//��������
	private String RECORD_DATE;
	//���۵�������
	private int amount;
	//�ܼ�Ǯ
	private double totalPrice;
	//��Ʒ�����ۼ�¼
	private Vector<goodSaleRecord> goodSaleRecords;
	
	//�ü�¼�ж�Ӧ������Ʒ������, ��ʾ��
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
