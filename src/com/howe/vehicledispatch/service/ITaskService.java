package com.howe.vehicledispatch.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.util.Page;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface ITaskService {
	
	public void create(Task task);
	
	public void delete(Integer id);
	
	public void update(Task task);
	
	public Task findTaskById(Class<Task> clazz,Integer id);
	
	public List<Task> findAllTask();
	
	public List<Task> searchTask(Task task);
	
	public List<Driver> findUseableDriver();
	
	public List<Car> findUseableCar();
	
	public Page<Task> test(Task task, Page<Task> page);
	
	@SuppressWarnings("rawtypes")
	public Pager taskPager(int start, String hql);
	

}
