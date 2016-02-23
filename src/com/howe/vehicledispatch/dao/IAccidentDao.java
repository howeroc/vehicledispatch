package com.howe.vehicledispatch.dao;

import java.util.Date;
import java.util.List;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;


public interface IAccidentDao extends IDao<Accident>{
	 
	 public List<Accident> searchAccident(Accident accident, Date endDate);
	 
	 public List<Car> findAllCar();
	 
	 public List<Driver> findAllDriver();
	 
}
