package com.howe.vehicledispatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.dao.IFixDao;
import com.howe.vehicledispatch.service.IFixService;
import com.howe.vehicledispatch.util.Pager;

@Service("IFixService")
public class FixServiceImpl implements IFixService {

	@Autowired
	public IFixDao fixDao;
	public Fix fix;
	public List<Fix> fixs;
	
	@Override
	public void create(Fix fix) {
		
		this.getFixDao().insert(fix);
	}

	@Override
	public void delete(Integer id) {

		fix = findFixById(Fix.class, id);
		
		this.getFixDao().delete(fix);
	}

	@Override
	public void update(Fix fix) {

		this.getFixDao().update(fix);
	}

	@Override
	public Fix findFixById(Class<Fix> clazz, Integer id) {
		
		return this.getFixDao().findById(clazz, id);
	}

	@Override
	public List<Fix> findAllFix() {
		
		fixs = this.getFixDao().findAll();
		
		if (fixs.size() > 0)
			
			return fixs;
		
		return null;
	}

	@Override
	public List<Car> findUseableCar() {
		
		List<Car> cars = this.getFixDao().findUseableCar();
		
		if(cars.size() >0 )
			
			return cars;
		
		return null;
	}
	
	@Override
	public List<Fix> searchFix(Fix fix) {

		List<Fix> fixs = this.getFixDao().searchFix(fix);
		
		if(fixs != null){
			
			return fixs;
		}
		
		return null;
	}

	public IFixDao getFixDao() {
		return fixDao;
	}

	public void setFixDao(IFixDao fixDao) {
		this.fixDao = fixDao;
	}

	public Fix getFix() {
		return fix;
	}

	public void setFix(Fix fix) {
		this.fix = fix;
	}

	public List<Fix> getFixs() {
		return fixs;
	}

	public void setFixs(List<Fix> fixs) {
		this.fixs = fixs;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager fixPager(int start, String hql) {

		return this.getFixDao().pager(start, hql);
	}


}
