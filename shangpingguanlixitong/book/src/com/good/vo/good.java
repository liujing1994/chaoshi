package com.good.vo;

/**
 * ��Ʒ����
 * 

 */
public class good extends ValueObject {
	//��Ʒ����
	private String good_NAME;
	//���
	private String good_INTRO;
	//��Ʒ�ĵ���
	private String good_PRICE;
	//�������
	private String TYPE_ID_FK;
	//�̼����
	private String PUB_ID_FK;
	//�洢��
	private String REPERTORY_SIZE;
	//����ͼurl
	private String IMAGE_URL;
	
	//��Ʒ���࣬�����ݿ��ѯ������ʱ���������Ϊnull����ͨ�������TYPE_ID_FKȥ�����������
	private Type type;
	
	//��Ʒ��Ӧ���̼ң���type��ͬ
	private Concern concern;
	
	private String AUTHOR;
	
	public good() {
		
	}
	
	public good(String id, String good_name, String good_intro, String good_price,
			String type_id_fk, String pub_id_fk, String repertory_size, 
			String image_url, String author) {
		setID(id);
		good_NAME = good_name;
		good_INTRO = good_intro;
		good_PRICE = good_price;
		TYPE_ID_FK = type_id_fk;
		PUB_ID_FK = pub_id_fk;
		REPERTORY_SIZE = repertory_size;
		IMAGE_URL = image_url;
		AUTHOR = author;
	}

	public String getgood_NAME() {
		return good_NAME;
	}

	public void setgood_NAME(String good_name) {
		good_NAME = good_name;
	}

	public String getgood_INTRO() {
		return good_INTRO;
	}

	public void setgood_INTRO(String good_intro) {
		good_INTRO = good_intro;
	}

	public String getgood_PRICE() {
		return good_PRICE;
	}

	public void setgood_PRICE(String good_price) {
		good_PRICE = good_price;
	}

	public String getTYPE_ID_FK() {
		return TYPE_ID_FK;
	}

	public void setTYPE_ID_FK(String type_id_fk) {
		TYPE_ID_FK = type_id_fk;
	}

	public String getPUB_ID_FK() {
		return PUB_ID_FK;
	}

	public void setPUB_ID_FK(String pub_id_fk) {
		PUB_ID_FK = pub_id_fk;
	}

	public String getREPERTORY_SIZE() {
		return REPERTORY_SIZE;
	}

	public void setREPERTORY_SIZE(String repertory_size) {
		REPERTORY_SIZE = repertory_size;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Concern getConcern() {
		return concern;
	}

	public void setConcern(Concern concern) {
		this.concern = concern;
	}

	public String getIMAGE_URL() {
		return IMAGE_URL;
	}

	public void setIMAGE_URL(String image_url) {
		IMAGE_URL = image_url;
	}

	public String getAUTHOR() {
		return AUTHOR;
	}

	public void setAUTHOR(String author) {
		AUTHOR = author;
	}
	
}
