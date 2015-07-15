package com.good.vo;


/**
 * 商品的销售记录
 */
public class goodSaleRecord extends ValueObject {

	//该记录对应的商品的外键
	private String good_ID_FK;
	
	//该记录对应的销售记录的外键
	private String T_SALE_RECORD_ID_FK;
	
	//该记录所对应的商品的销售数量
	private String TRADE_SUM;
	
	//该记录对应的商品对象, 当从数据库查找到goodSaleRecord时, 该属性为null
	private good good;
	
	//该记录对应的销售记录对象, 当从数据库查找到goodSaleRecord时, 该属性为null
	private SaleRecord saleRecord;

	public String getgood_ID_FK() {
		return good_ID_FK;
	}

	public void setgood_ID_FK(String good_id_fk) {
		good_ID_FK = good_id_fk;
	}

	public String getT_SALE_RECORD_ID_FK() {
		return T_SALE_RECORD_ID_FK;
	}

	public void setT_SALE_RECORD_ID_FK(String t_sale_record_id_fk) {
		T_SALE_RECORD_ID_FK = t_sale_record_id_fk;
	}

	public good getgood() {
		return good;
	}

	public void setgood(good good) {
		this.good = good;
	}

	public SaleRecord getSaleRecord() {
		return saleRecord;
	}

	public void setSaleRecord(SaleRecord saleRecord) {
		this.saleRecord = saleRecord;
	}

	public String getTRADE_SUM() {
		return TRADE_SUM;
	}

	public void setTRADE_SUM(String trade_sum) {
		TRADE_SUM = trade_sum;
	}
	
	
}
