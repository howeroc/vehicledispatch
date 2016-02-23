package com.howe.vehicledispatch.dao.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.dao.ITaskDao;
import com.howe.vehicledispatch.util.Page;
import com.howe.vehicledispatch.util.StringUtil;

@Repository("ITaskDao")
public class TaskDaoImpl extends DaoImpl<Task> implements ITaskDao {

	public TaskDaoImpl() {
		super(Task.class); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByDriver(String name) {
		
		List<Task> tasks = crtQuery("SELECT t FROM Task t WHERE d.driver.name =:name").setParameter("name", name).list();
		
		if (tasks != null)
			
			return tasks;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByDriver(Integer id) {
		
		List<Task> tasks = crtQuery("SELECT t FROM Task t WHERE d.driver.id =:id").setParameter("id", id).list();
		
		if (tasks != null)
			
			return tasks;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByCar(Integer id) {
		
		List<Task> tasks = crtQuery("SELECT t FROM Task t WHERE d.car.id =:id").setParameter("id", id).list();
		
		if (tasks != null)
			
			return tasks;
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByTime(Date start, Date end) {
		
		Query query = crtQuery("SELECT t FROM Task t WHERE d.leaveDate >=:startDate and d.leaveDate<=:endDate");
		
		query.setDate("leaveDate", end);
		
		query.setDate("startDate", start);
		
		List<Task> tasks = query.list();
		
		if (tasks != null)
			
			return tasks;
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> findUseableDriver(){
		
		List<Driver> drivers = crtQuery("SELECT d FROM Driver d WHERE d.mode = 1  ").list();
		
		if(drivers.size() > 0)
			
			return drivers;
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> findUseableCar(){
		
		List<Car> cars = crtQuery("SELECT c FROM Car c WHERE c.mode = 1  ").list();
		
		if(cars != null)
			
			return cars;
		
		return null;
	}

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
		
		List<Task> tasks = this.qryInfo(bf);
		
		if(tasks != null)
			
		return tasks;
		
		return null;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Page<Task> pageTask(Task task, Page<Task> page) {

		Query query = this.getSessionFactory().getCurrentSession().createQuery("select count(*) from Task");
		Long count = (Long) query.uniqueResult();
		page.setTotalCount(count);
		query = this.getSessionFactory().getCurrentSession().createQuery("from Task");
		query.setFirstResult((int) page.getStartNum());
		query.setMaxResults((int) page.getPageSize());
		page.setList(query.list());
		return page;
	}


}
