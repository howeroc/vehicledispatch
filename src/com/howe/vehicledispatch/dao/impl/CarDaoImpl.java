package com.howe.vehicledispatch.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.dao.ICarDao;

@Repository("ICarDao")
public class CarDaoImpl extends DaoImpl<Car> implements ICarDao{
	
	public CarDaoImpl() {
		super(Car.class); 
	}
	
	@Override
	public Car findById(Class<Car> clazz, Integer id){
		return (Car) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Car findByLicen(String licenId) {
		
		List<Car> cars = crtQuery("SELECT c FROM Car c WHERE c.licenId=:licenId").setParameter("licenId", licenId).list();
		
		if (cars.size() > 0)
			return cars.get(0);
		
		return null;
	}
}
