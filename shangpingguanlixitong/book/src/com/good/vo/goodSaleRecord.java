package com.good.vo;


/**
 * ��Ʒ�����ۼ�¼
 */
public class goodSaleRecord extends ValueObject {

	//�ü�¼��Ӧ����Ʒ�����
	private String good_ID_FK;
	
	//�ü�¼��Ӧ�����ۼ�¼�����
	private String T_SALE_RECORD_ID_FK;
	
	//�ü�¼����Ӧ����Ʒ����������
	private String TRADE_SUM;
	
	//�ü�¼��Ӧ����Ʒ����, �������ݿ���ҵ�goodSaleRecordʱ, ������Ϊnull
	private good good;
	
	//�ü�¼��Ӧ�����ۼ�¼����, �������ݿ���ҵ�goodSaleRecordʱ, ������Ϊnull
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
