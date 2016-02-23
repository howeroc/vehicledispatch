package com.howe.vehicledispatch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.dao.ITaskDao;
import com.howe.vehicledispatch.service.ITaskService;
import com.howe.vehicledispatch.util.Page;
import com.howe.vehicledispatch.util.Pager;

@Service("ITaskService")
public class TaskServiceImpl implements ITaskService {

	@Autowired
	public ITaskDao taskDao;
	
	public Task task;
	
	public List<Task> tasks;
	
	@Override
	public void create(Task task) {

		this.getTaskDao().insert(task);

	}

	@Override
	public void delete(Integer id) {

		task = findTaskById(Task.class, id);
		
		this.getTaskDao().delete(task);

	}

	@Override
	public void update(Task task) {

		this.getTaskDao().update(task);

	}

	@Override
	public Task findTaskById(Class<Task> clazz, Integer id) {
		
		return this.getTaskDao().findById(clazz, id);
	}

	@Override
	public List<Task> findAllTask() {
		
		tasks = this.getTaskDao().findAll();
		
		if (tasks != null)
			
			return tasks;
		
		return null;
	}

	@Override
	public List<Driver> findUseableDriver() {
		
		List<Driver> drivers = this.getTaskDao().findUseableDriver();
		
		if( drivers != null)
			
			return drivers;
		
		return null;
	}

	@Override
	public List<Car> findUseableCar() {
		
		List<Car> cars = this.getTaskDao().findUseableCar();
		
		if(cars != null )
			
			return cars;
		
		return null;
	}
	
	@Override
	public List<Task> searchTask(Task task) {

		List<Task> tasks = this.getTaskDao().searchTask(task);
		
		if(tasks != null){
			
			return tasks;
		}
		
		return null;
	}

	public ITaskDao getTaskDao() {
		return taskDao;
	}

	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public Page<Task> test(Task task, Page<Task> page) {
		return this.getTaskDao().pageTask(task, page);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pager taskPager(int start, String hql) {

		return this.getTaskDao().pager(start, hql);
	}

}
