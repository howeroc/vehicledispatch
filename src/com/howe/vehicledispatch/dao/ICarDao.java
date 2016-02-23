package com.howe.vehicledispatch.dao;

import com.howe.vehicledispatch.bean.Car;

public interface ICarDao extends IDao<Car>{
	
	 public Car findByLicen(String licenId);
	 
	 public Car findById(Class<Car> clazz, Integer id);

}
