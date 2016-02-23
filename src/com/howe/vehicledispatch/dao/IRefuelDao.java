package com.howe.vehicledispatch.dao;

import java.util.Date;
import java.util.List;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Refuel;

public interface IRefuelDao extends IDao<Refuel>{
	 
	 public List<Refuel> searchRefuel(Refuel refuel, Date endDate);
	 
	 public List<Car> findAllCar();
	 
}
