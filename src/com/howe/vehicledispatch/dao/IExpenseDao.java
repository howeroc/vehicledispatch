package com.howe.vehicledispatch.dao;

import java.util.Date;
import java.util.List;

import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.bean.Task;

public interface IExpenseDao{
	 
	 public List<Task> searchTask(Task task);
	 
	 public List<Refuel> searchRefuel(Refuel refuel, Date endDate);
	 
	 public List<Fix> searchFix(Fix fix);
	 
	 public List<Accident> searchAccident(Accident accident, Date endDate);
}
