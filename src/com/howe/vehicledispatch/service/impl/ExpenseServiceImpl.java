package com.howe.vehicledispatch.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.dao.IExpenseDao;
import com.howe.vehicledispatch.service.IExpenseService;

@Service("IExpenseService")
public class ExpenseServiceImpl implements IExpenseService {

	@Autowired
	public IExpenseDao expenseDao;
	
	@Override
	public List<Task> searchTask(Task task) {
		
		return this.getExpenseDao().searchTask(task);
	}
	
	@Override
	public List<Refuel> searchRefuel(Refuel refuel, Date endDate) {
		
		return this.getExpenseDao().searchRefuel(refuel, endDate);
	}
	
	@Override
	public List<Fix> searchFix(Fix fix) {
		
		return this.getExpenseDao().searchFix(fix);
	}
	@Override
	public List<Accident> searchAccident(Accident accident, Date endDate) {

		return this.getExpenseDao().searchAccident(accident, endDate);
	}
	public IExpenseDao getExpenseDao() {
		return expenseDao;
	}
	public void setExpenseDao(IExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

}
