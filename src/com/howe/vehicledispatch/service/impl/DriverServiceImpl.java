package com.howe.vehicledispatch.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.dao.IDriverDao;
import com.howe.vehicledispatch.service.IDriverService;
import com.howe.vehicledispatch.util.Pager;

@Service("IDriverService")
public class DriverServiceImpl implements IDriverService{
	
	@Autowired
	private IDriverDao driverDao;
	
	private Driver driver;
	
	@Override
	public Driver findDriverByName(String name) {
		return this.getDriverDao().findByName(name);
	}

	@Override
	public void create(Driver driver) {
		
		if(findDriverByName(driver.getName()) !=null){
			
			throw new RuntimeException("用户" + driver.getName() + "已存在。");
		}
		
		this.getDriverDao().insert(driver);		
	}
	
	@Override
	public void delete(Integer id) {
		
		driver = findDriverById(Driver.class, id);
		
		this.getDriverDao().delete(driver);
	}
	
	@Override
	public void update(Driver driver) {

		this.driverDao.update(driver);
	}	

	@Override
	public Driver findDriverById(Class<Driver> clazz,Integer id) {
			
			return this.getDriverDao().findById( clazz, id);

	}

	@Override
	public List<Driver> findAllDriver(){
		
		List<Driver> list = this.getDriverDao().findAll();
		
		if (list.size() > 0)
			
			return list;
		
		return null;
	}

	public IDriverDao getDriverDao() {
		return driverDao;
	}

	public void setDriverDao(IDriverDao driverDao) {
		this.driverDao = driverDao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager driverPager(int start, String hql) {

		return this.getDriverDao().pager(start, hql);
	}
	
}
