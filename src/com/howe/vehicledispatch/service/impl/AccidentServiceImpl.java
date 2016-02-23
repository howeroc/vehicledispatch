package com.howe.vehicledispatch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.dao.IAccidentDao;
import com.howe.vehicledispatch.service.IAccidentService;
import com.howe.vehicledispatch.util.Pager;

@Service("IAccidentService")
public class AccidentServiceImpl implements IAccidentService {

	@Autowired
	public IAccidentDao accidentDao;
	
	public Accident accident;
	
	public List<Accident> accidents;
	
	@Override
	public void create(Accident accident) {

		this.getAccidentDao().insert(accident);

	}

	@Override
	public void delete(Integer id) {

		accident = this.getAccidentDao().findById(Accident.class, id);
		
		this.getAccidentDao().delete(accident);

	}

	@Override
	public void update(Accident accident) {

		this.getAccidentDao().update(accident);

	}

	@Override
	public Accident findAccidentById(Class<Accident> clazz, Integer id) {
		
		return this.getAccidentDao().findById(clazz, id);
	}

	@Override
	public List<Accident> findAllAccident() {
		
		accidents = this.getAccidentDao().findAll();
		
		if (accidents.size() > 0)
			
			return accidents;
		
		return null;
	}

	
	@Override
	public List<Accident> searchAccident(Accident accident, Date endDate) {

		List<Accident> accidents = this.getAccidentDao().searchAccident(accident, endDate);
		
		if(accidents != null){
			
			return accidents;
		}
		
		return null;
	}

	@Override
	public List<Car> findAllCar() {
		
		return this.getAccidentDao().findAllCar();
	}

	@Override
	public List<Driver> findAllDriver() {
		
		return this.getAccidentDao().findAllDriver();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Pager accidentPager(int start, String hql) {
		
		return this.getAccidentDao().pager(start, hql);
	}

	public IAccidentDao getAccidentDao() {
		return accidentDao;
	}

	public void setAccidentDao(IAccidentDao accidentDao) {
		this.accidentDao = accidentDao;
	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}

	public List<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
	}

}
