package com.howe.vehicledispatch.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface ICarService{
	
	public Car findCarByLicen(String licenId);
	
	public void create(Car car);
	
	public void delete(Integer id);
	
	public void update(Car car);
	
	public Car findCarById(Class<Car> clazz,Integer id);
	
	public List<Car> findAllCar();
	
	@SuppressWarnings("rawtypes")
	public Pager carPager(int start, String hql);
}
