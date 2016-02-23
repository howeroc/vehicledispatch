package com.howe.vehicledispatch.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.dao.IFixDao;

@Repository("IFixDao")
public class FixDaoImpl extends DaoImpl<Fix> implements IFixDao {

	public FixDaoImpl() {
		super(Fix.class); 
	}
	
	

	@Override
	public List<Fix> searchFix(Fix fix) {

		StringBuffer buffer = new StringBuffer(" SELECT f FROM Fix f WHERE 1=1");
		
		if(fix.getId()!=null){
			
			buffer.append(" AND f.id="+ fix.getId());
			
		}
		
		if(fix.getCar().getId()!=null){
			
			buffer.append(" AND f.car.id="+ fix.getCar().getId());
		}
		
		if(fix.getLeaveTime()!=null){
			
			buffer.append(" AND r.leaveTime >="+ fix.getLeaveTime());
		}
		
		if(fix.getBackTime()!=null){
			
			buffer.append(" AND r.leaveTime =<"+ fix.getBackTime());
		}
		
		String bf = buffer.toString();
		
		List<Fix> fixs = this.qryInfo(bf);
		
		if(fixs != null)
			
		return fixs;
		
		return null;
	}



	@Override
	public List<Car> findUseableCar(){
		
		@SuppressWarnings("unchecked")
		List<Car> cars = crtQuery("SELECT c FROM Car c WHERE (c.mode = 1 or c.mode = 0) ").list();
		
		if(cars.size() > 0)
			
			return cars;
		
		return null;
	}

}
