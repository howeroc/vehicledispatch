package com.howe.vehicledispatch.dao;

import java.util.List;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Fix;

public interface IFixDao extends IDao<Fix>{
	 
	 public List<Fix> searchFix(Fix fix);
	 
	 public List<Car> findUseableCar();
	 
}
