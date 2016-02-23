package com.howe.vehicledispatch.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.howe.vehicledispatch.util.StringUtil;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.dao.IAccidentDao;

@Repository("IAccidentDao")
public class AccidentDaoImpl extends DaoImpl<Accident> implements IAccidentDao {

	public AccidentDaoImpl() {
		super(Accident.class); 
	}

	@Override
	public List<Accident> searchAccident(Accident accident, Date endDate) {

		StringBuffer buffer = new StringBuffer(" SELECT  a FROM Accident a WHERE 1=1");
		
		if(accident.getId()!=null){
			
			buffer.append(" AND a.id="+ accident.getId());
			
		}
		
		if(accident.getCar().getId()!=null){
			
			buffer.append(" AND a.car.id="+ accident.getCar().getId());
		}
		
		if(accident.getDriver().getId()!=null){
			
			buffer.append(" AND a.driver.id="+ accident.getDriver().getId());
		}
		
		if(!StringUtil.isNull(accident.getDriver().getName())){
			
			buffer.append(" AND a.driver.name Like '%"+ accident.getDriver().getName()+"%'");
		}
		
		if(accident.getAccDate()!=null){
			
			buffer.append(" AND a.accDate >="+ accident.getAccDate());
		}
		
		if(endDate!=null){
			
			buffer.append(" AND a.accDate =<"+ endDate);
		}

		String bf = buffer.toString();
		
		List<Accident> accidents = this.qryInfo(bf);
		
		if(accidents != null)
			
		return accidents;
		
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> findAllCar() {
		
		List<Car> cars = crtQuery("From Car ").list();
		
		if(cars.size() > 0)
			
			return cars;
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> findAllDriver() {
		
		List<Driver> drivers = crtQuery("From Driver ").list();
		
		if(drivers.size() > 0)
			
			return drivers;
		
		return null;
	}

}
