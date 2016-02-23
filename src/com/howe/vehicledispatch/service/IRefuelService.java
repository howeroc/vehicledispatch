package com.howe.vehicledispatch.service;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IRefuelService {
	
	public void create(Refuel refuel);
	
	public void delete(Integer id);
	
	public void update(Refuel refuel);
	
	public Refuel findRefuelById(Class<Refuel> clazz,Integer id);
	
	public List<Refuel> findAllRefuel();
	
	public List<Car> findAllCar();
	
	public List<Refuel> searchRefuel(Refuel refuel, Date endDate);

	@SuppressWarnings("rawtypes")
	public Pager refuelPager(int start, String hql);
	

}
