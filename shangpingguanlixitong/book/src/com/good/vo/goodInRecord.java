package com.good.vo;

/**
 * 商品入库记录对象
 * 

 */
public class goodInRecord extends ValueObject {
	//对应商品的外键, 从数据库查出来时有值
	private String good_ID_FK;
	//对应销售记录外键
	private String T_IN_RECORD_ID_FK;
	//入库数量
	private String IN_SUM;
	
	//该记录所对应的商品, 从数据库查出来时为null
	private good good;
	
	//该记录所对应的和库记录, 从数据库查出来时为null
	private InRecord inRecord;

	public String getgood_ID_FK() {
		return good_ID_FK;
	}

	public void setgood_ID_FK(String good_id_fk) {
		good_ID_FK = good_id_fk;
	}

	public String getT_IN_RECORD_ID_FK() {
		return T_IN_RECORD_ID_FK;
	}

	public void setT_IN_RECORD_ID_FK(String t_in_record_id_fk) {
		T_IN_RECORD_ID_FK = t_in_record_id_fk;
	}

	public String getIN_SUM() {
		return IN_SUM;
	}

	public void setIN_SUM(String in_sum) {
		IN_SUM = in_sum;
	}

	public good getgood() {
		return good;
	}

	public void setgood(good good) {
		this.good = good;
	}

	public InRecord getInRecord() {
		return inRecord;
	}

	public void setInRecord(InRecord inRecord) {
		this.inRecord = inRecord;
	}
	
}
