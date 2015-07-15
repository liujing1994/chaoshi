package com.good.service.impl;

import java.util.Collection;

import com.good.dao.goodDao;
import com.good.dao.ConcernDao;
import com.good.dao.TypeDao;
import com.good.service.goodService;
import com.good.vo.good;
import com.good.vo.Concern;
import com.good.vo.Type;

/**
 * ��Ʒҵ��ʵ����
 
 */
public class goodServiceImpl implements goodService {

	private goodDao goodDao;
	
	private TypeDao typeDao;
	
	private ConcernDao concernDao;
	
	public goodServiceImpl(goodDao goodDao, TypeDao typeDao, ConcernDao concernDao) {
		this.goodDao = goodDao;
		this.typeDao = typeDao;
		this.concernDao = concernDao;
	}
	
 
	public good get(String id) {
		good good = goodDao.find(id);
		//������Ʒ��Ӧ������
		Type type = typeDao.find(good.getTYPE_ID_FK());
		//������Ʒ���̼�
		Concern concern = concernDao.find(good.getPUB_ID_FK());
		good.setType(type);
		good.setConcern(concern);
		return good;
	}

	 
	public Collection<good> getAll() {
		Collection<good> result = goodDao.findAll();
		//����setAssociate�������ù�������������
		return setAssociate(result);
	}
	
	//���ù�ϵ����
	private Collection<good> setAssociate(Collection<good> result) {
		//����������ϣ�����ÿһ����Ʒ�Ķ���
		for (good good : result) {
			//���ҳ���Ӧ�����࣬��Ϊ��Ʒ�����������
			good.setType(typeDao.find(good.getTYPE_ID_FK()));
			//���ҳ���Ӧ���̼ң���Ϊ��Ʒ�����̼Ҷ���
			good.setConcern(concernDao.find(good.getPUB_ID_FK()));
		}
		return result;
	}

	 
	public good add(good good) {
		String id = goodDao.add(good);
		return get(id);
	}

	 
	public good update(good good) {
		String id = goodDao.update(good);
		return get(id);
	}

	 
	public Collection<good> find(String name) {
		Collection<good> result = goodDao.findByName(name);
		return setAssociate(result);
	}
}
