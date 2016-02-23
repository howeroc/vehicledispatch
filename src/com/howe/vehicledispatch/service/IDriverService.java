package com.howe.vehicledispatch.service;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IDriverService{
	
	public Driver findDriverByName(String name);
	
	public void create(Driver driver);
	
	public void delete(Integer id);
	
	public void update(Driver driver);
	
	public Driver findDriverById(Class<Driver> clazz,Integer id);
	
	public List<Driver> findAllDriver();
	
	@SuppressWarnings("rawtypes")
	public Pager driverPager(int start, String hql);
}
