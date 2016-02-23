package com.howe.vehicledispatch.service;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IAccidentService {
	
	public void create(Accident accident);
	
	public void delete(Integer id);
	
	public void update(Accident accident);
	
	public Accident findAccidentById(Class<Accident> clazz,Integer id);
	
	public List<Accident> findAllAccident();
	
	public List<Accident> searchAccident(Accident accident, Date endDate);	
	
	public List<Car> findAllCar();
	
	public List<Driver> findAllDriver();
	
	@SuppressWarnings("rawtypes")
	public Pager accidentPager(int start, String hql);
}
