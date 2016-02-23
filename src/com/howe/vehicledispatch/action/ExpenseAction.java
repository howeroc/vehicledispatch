package com.howe.vehicledispatch.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.service.IExpenseService;

@SuppressWarnings("serial")
@Controller
public class ExpenseAction extends IAction{
	
	@Autowired
	private IExpenseService expenseService;
	private Task task;
	private Refuel refuel;
	private Fix fix;
	private Accident accident;
	private List<Task> tasks;
	private List<Refuel> refuels;
	private List<Accident> accidents;
	
	public String execute(){
		
		if ("add".equals(action))
			
			return "";
		
		if ("search".equals(action))
			
			return "";
		if ("delete".equals(action))
			
			return "";
		if ("edit".equals(action))
			
			return "";	
		if ("listAll".equals(action))
			
			return "";
		return "fail";
	}
	public IExpenseService getExpenseService() {
		return expenseService;
	}
	public void setExpenseService(IExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Refuel getRefuel() {
		return refuel;
	}
	public void setRefuel(Refuel refuel) {
		this.refuel = refuel;
	}
	public Fix getFix() {
		return fix;
	}
	public void setFix(Fix fix) {
		this.fix = fix;
	}
	public Accident getAccident() {
		return accident;
	}
	public void setAccident(Accident accident) {
		this.accident = accident;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public List<Refuel> getRefuels() {
		return refuels;
	}
	public void setRefuels(List<Refuel> refuels) {
		this.refuels = refuels;
	}
	public List<Accident> getAccidents() {
		return accidents;
	}
	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
	}

}
