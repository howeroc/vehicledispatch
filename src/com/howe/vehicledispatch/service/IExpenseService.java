package com.howe.vehicledispatch.service;

import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.bean.Task;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IExpenseService {
	
	 public List<Task> searchTask(Task task);
	 
	 public List<Refuel> searchRefuel(Refuel refuel, Date endDate);
	 
	 public List<Fix> searchFix(Fix fix);
	 
	 public List<Accident> searchAccident(Accident accident, Date endDate);
	 
}
