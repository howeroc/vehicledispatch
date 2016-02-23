package com.howe.vehicledispatch.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.howe.vehicledispatch.util.StringUtil;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.dao.IExpenseDao;

@Repository("IExpenseDao")
public class ExpenseDaoImpl  implements IExpenseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> searchTask(Task task) {

		StringBuffer buffer = new StringBuffer(" SELECT  t FROM Task t WHERE 1=1");
		
		if(task.getId()!=null){
			
			buffer.append(" AND t.id="+ task.getId());
			
		}
		
		if(task.getCar().getId()!=null){
			
			buffer.append(" AND t.car.id="+ task.getCar().getId());
		}
		
		if(task.getDriver().getId()!=null){
			
			buffer.append(" AND t.driver.id="+ task.getDriver().getId());
		}
		
		if(!StringUtil.isNull(task.getDriver().getName())){
			
			buffer.append(" AND t.driver.name Like '%"+ task.getDriver().getName()+"%'");
		}
		
		if(task.getLeaveTime()!=null){
			
			buffer.append(" AND t.leaveTime >="+ task.getLeaveTime());
		}
		
		if(task.getBackTime()!=null){
			
			buffer.append(" AND t.leaveTime =<"+ task.getBackTime());
		}
		
		if(!StringUtil.isNull(task.getDest())){
			
			buffer.append(" AND t.dest LIKE '%"+ task.getDest() + "%'");
		}
		
		if(!StringUtil.isNull(task.getMode())){
			
			buffer.append(" AND t.mode ="+ task.getMode());
		}
		
		String bf = buffer.toString();
		
		List<Task> tasks = sessionFactory.getCurrentSession().createQuery(bf).list();
		
		if(tasks != null)
			
		return tasks;
		
		return null;
	}

	@SuppressWarnings("unchecked")
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
		
		List<Refuel> refuels = sessionFactory.getCurrentSession().createQuery(bf).list();
		
		if(refuels!= null)
			
		return refuels;
		
		return null;
	}

	@SuppressWarnings("unchecked")
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
		
		List<Fix> fixs = sessionFactory.getCurrentSession().createQuery(bf).list();
		
		if(fixs != null)
			
		return fixs;
		
		return null;
	}


	@SuppressWarnings("unchecked")
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
		
		List<Accident> accidents = sessionFactory.getCurrentSession().createQuery(bf).list();
		
		if(accidents != null)
			
		return accidents;
		
		return null;
	}

}
