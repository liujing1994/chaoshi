package com.good.vo;

import java.util.Vector;

/**
 * ����¼
 * 

 */
public class InRecord extends ValueObject {

	//�������
	private String RECORD_DATE;
	
	//����������
	private int amount;
	
	//������¼����Ӧ����Ʒ������¼
	private Vector<goodInRecord> goodInRecords;
	
	//�����Ʒ������, �Զ��Ÿ���
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
