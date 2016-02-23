package com.howe.vehicledispatch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.dao.IRefuelDao;
import com.howe.vehicledispatch.service.IRefuelService;
import com.howe.vehicledispatch.util.Pager;

@Service("IRefuelService")
public class RefuelServiceImpl implements IRefuelService {

	@Autowired
	public IRefuelDao refuelDao;
	public Refuel refuel;
	public List<Refuel> refuels;
	
	@Override
	public void create(Refuel refuel) {
		this.getRefuelDao().insert(refuel);
	}

	@Override
	public void delete(Integer id) {

		refuel = findRefuelById(Refuel.class, id);
		
		this.getRefuelDao().delete(refuel);
	}

	@Override
	public void update(Refuel refuel) {

		this.getRefuelDao().update(refuel);

	}

	@Override
	public Refuel findRefuelById(Class<Refuel> clazz, Integer id) {
		
		return this.getRefuelDao().findById(clazz, id);
	}

	@Override
	public List<Refuel> findAllRefuel() {
		
		refuels = this.getRefuelDao().findAll();
		
		if (refuels.size() > 0)
			
			return refuels;
		
		return null;
	}
	
	@Override
	public List<Refuel> searchRefuel(Refuel refuel, Date endDate) {

		List<Refuel> refuels = this.getRefuelDao().searchRefuel(refuel, endDate);
		
		if(refuels != null){
			
			return refuels;
		}
		
		return null;
	}

	public IRefuelDao getRefuelDao() {
		return refuelDao;
	}

	public void setRefuelDao(IRefuelDao refuelDao) {
		this.refuelDao = refuelDao;
	}

	public Refuel getRefuel() {
		return refuel;
	}

	public void setRefuel(Refuel refuel) {
		this.refuel = refuel;
	}

	public List<Refuel> getRefuels() {
		return refuels;
	}

	public void setRefuels(List<Refuel> refuels) {
		this.refuels = refuels;
	}

	@Override
	public List<Car> findAllCar() {
		
		List<Car> cars = this.getRefuelDao().findAllCar();
		
		if(cars != null){
			
			return cars;
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager refuelPager(int start, String hql) {
		
		return this.getRefuelDao().pager(start, hql);
	}
}
