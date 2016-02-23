package com.howe.vehicledispatch.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Refuel;

import com.howe.vehicledispatch.dao.IRefuelDao;


@Repository("IRefuelDao")
public class RefuelDaoImpl extends DaoImpl<Refuel> implements IRefuelDao {

	public RefuelDaoImpl() {
		super(Refuel.class); 
	}
	
	@Override
	public List<Refuel> searchRefuel(Refuel refuel, Date endDate) {

		StringBuffer buffer = new StringBuffer(" SELECT  r FROM Refuel r WHERE 1=1");
		
		if(refuel.getId()!=null){
			
			buffer.append(" AND r.id="+ refuel.getId());
			
		}
		
		if(refuel.getCar().getId()!=null){
			
			buffer.append(" AND t.car.id="+ refuel.getCar().getId());
		}
		

		if(refuel.getReDate()!=null){
			
			buffer.append(" AND r.reDate >="+ refuel.getReDate());
		}
		
		if(endDate!=null){
			
			buffer.append(" AND r.reDate =<"+endDate);
		}

		
		String bf = buffer.toString();
		
		List<Refuel> refuels = this.qryInfo(bf);
		
		if(refuels!= null)
			
		return refuels;
		
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


}
