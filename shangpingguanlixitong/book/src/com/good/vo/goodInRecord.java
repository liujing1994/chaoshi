package com.good.vo;

/**
 * ��Ʒ����¼����
 * 

 */
public class goodInRecord extends ValueObject {
	//��Ӧ��Ʒ�����, �����ݿ�����ʱ��ֵ
	private String good_ID_FK;
	//��Ӧ���ۼ�¼���
	private String T_IN_RECORD_ID_FK;
	//�������
	private String IN_SUM;
	
	//�ü�¼����Ӧ����Ʒ, �����ݿ�����ʱΪnull
	private good good;
	
	//�ü�¼����Ӧ�ĺͿ��¼, �����ݿ�����ʱΪnull
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
