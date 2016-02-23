package com.howe.vehicledispatch.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.dao.IDriverDao;

@Repository("IDriverDao")
public class DriverDaoImpl extends DaoImpl<Driver> implements IDriverDao{
	
	public DriverDaoImpl() {
		
		super(Driver.class); 
	}
	
	@Override
	public Driver findById(Class<Driver> clazz, Integer id){
		
		return (Driver) sessionFactory.getCurrentSession().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Driver findByName(String name) {
		
		List<Driver> drivers = crtQuery("SELECT d FROM Driver d WHERE d.name =:name").setParameter("name", name).list();
		
		if (drivers.size() > 0)
			
			return drivers.get(0);
		
		return null;
	}
	
}
