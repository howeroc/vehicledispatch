package com.howe.vehicledispatch.action;

import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
public class  NavAction extends IAction {
	
public String execute(){
		
		try{
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 1)
				return "usersearch";
			
			if(param == 2)
				return "driversearch";
			
			if(param == 3)
				return "carsearch";
			
			if(param == 4)	
				return "tasksearch";
			
			if(param == 5)	
				return "refuelsearch";
			
			if(param == 6)	
				return "fixsearch";
			
			if(param == 7)	
				return "accidentsearch";
			
		}catch (Exception e){
			
			setMessage("·¢Éú´íÎó" + e.getMessage());			
		}
		
		return "fail";
	}
	
	protected String getParam(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}

}
