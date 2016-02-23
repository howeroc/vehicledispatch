package com.howe.vehicledispatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.dao.ICarDao;
import com.howe.vehicledispatch.service.ICarService;
import com.howe.vehicledispatch.util.Pager;

@Service("ICarService")
public class CarServiceImpl implements ICarService{
	
	@Autowired
	private ICarDao carDao;
	
	private Car car;
	
	@Override
	public Car findCarByLicen(String licenId) {
		
		return this.getCarDao().findByLicen(licenId);
	}

	@Override
	public void create(Car car) {
		
		if(findCarByLicen(car.getLicenId()) !=null){
			
			throw new RuntimeException("³µÅÆºÅ" + car.getLicenId() + "ÒÑ´æÔÚ¡£");
		}
		
		this.getCarDao().insert(car);		
	}
	
	@Override
	public void delete(Integer id) {
		
		car = findCarById(Car.class, id);
		
		this.getCarDao().delete(car);
	}
	
	@Override
	public void update(Car car) {

		this.carDao.update(car);
	}	

	@Override
	public Car findCarById(Class<Car> clazz,Integer id) {
			
			return this.getCarDao().findById( clazz, id);

	}

	@Override
	public List<Car> findAllCar(){
		
		List<Car> list = this.getCarDao().findAll();
		
		if (list.size() > 0)
			
			return list;
		
		return null;
	}

	public ICarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(ICarDao carDao) {
		this.carDao = carDao;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Pager carPager(int start, String hql) {

		return this.getCarDao().pager(start, hql);
	}
}
