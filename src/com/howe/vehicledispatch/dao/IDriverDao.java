package com.howe.vehicledispatch.dao;

import com.howe.vehicledispatch.bean.Driver;

public interface IDriverDao extends IDao<Driver>{
	
	 public Driver findByName(String name);
	 
	 public Driver findById(Class<Driver> clazz, Integer id);
	 
}
