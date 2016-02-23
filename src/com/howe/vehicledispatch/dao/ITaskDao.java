package com.howe.vehicledispatch.dao;

import java.util.Date;
import java.util.List;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.util.Page;

public interface ITaskDao extends IDao<Task>{
	
	 public List<Task> findByDriver(String name);
	 
	 public List<Task> findByDriver(Integer id);
	 
	 public List<Task> findByCar(Integer id);
	 
	 public List<Task> findByTime(Date start, Date end);
	 
	 public List<Driver> findUseableDriver();
	 
	 public List<Car> findUseableCar();
	 
	 public List<Task> searchTask(Task task);
	 
	 public Page<Task> pageTask(Task task, Page<Task> page);
}
