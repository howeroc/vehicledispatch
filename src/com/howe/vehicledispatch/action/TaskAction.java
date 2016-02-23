package com.howe.vehicledispatch.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Task;
import com.howe.vehicledispatch.service.ITaskService;
import com.howe.vehicledispatch.util.StringUtil;

@SuppressWarnings("serial")
@Controller
public class TaskAction extends IAction{
	
	@Autowired
	private ITaskService taskService;
	private Task task;
	private List<Task> tasks;
	private List<Driver> drivers;
	private List<Car> cars;
	private Date lDate;
	private Date bDate;
	private Integer sMile;
	
	public String execute(){
		
		if ("add".equals(action))
			
			return add();
		
		if ("search".equals(action))
			
			return search();
		if ("delete".equals(action))
			
			return delete();
		if ("edit".equals(action))
			
			return edit();	
		if ("listAll".equals(action))
			
			return listAll();
/*		if ("test".equals(action))
			
			return test();	*/	
		if("pager".equals(action))
			
			return pager();
			
		return "fail";
	}
	
	private String pager() {

		if(task == null&&lDate == null&&bDate == null&& pno==null){
			
			hql = "FROM Task";
			
			page = this.getTaskService().taskPager(1, hql);
			
			setMessage("搜索条件为空，默认显示所有信息！");
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			ServletActionContext.getRequest().setAttribute("searchInfo",hql);
			
			return "search";
		} else
			
		if( pno == null ){
			
			StringBuffer buffer = new StringBuffer(" SELECT  t FROM Task t WHERE 1=1");
			
			if(!StringUtil.isNull(task.getId())){
				
				buffer.append(" AND t.id="+ task.getId());
				
			}
			
			if(!StringUtil.isNull(task.getCar().getId())){
				
				buffer.append(" AND t.car.id="+ task.getCar().getId());
			}
			
			if(!StringUtil.isNull(task.getDriver().getId())){
				
				buffer.append(" AND t.driver.id="+ task.getDriver().getId());
			}
			
			if(!StringUtil.isNull(task.getDriver().getName())){
				
				buffer.append(" AND t.driver.name Like '%"+ task.getDriver().getName()+"%'");
			}
			
			
			if(!StringUtil.isNull(task.getDest())){
				
				buffer.append(" AND t.dest LIKE '%"+ task.getDest() + "%'");
			}
			
			if(!StringUtil.isNull(task.getMode())){
				
				buffer.append(" AND t.mode ="+ task.getMode());
			}
			if(!StringUtil.isNull(task.getMile())){
				
				buffer.append(" AND t.mile >="+ task.getMile());
			}
			if(!StringUtil.isNull(sMile)){
				
				buffer.append(" AND t.mile <="+ sMile);
			}
				
			if(task.getLeaveTime()!=null){
					String minDate = new SimpleDateFormat("yyyy-mm-dd").format(task.getLeaveTime());
					
					buffer.append(" AND t.leaveTime >= '"+ minDate+"'");
			}					
			
			if(lDate!=null){
					String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(lDate);
					buffer.append(" AND t.leaveTime >= '"+ maxDate +"'");
			}
			
			if(task.getLeaveTime()!=null){
					
					String minDate = new SimpleDateFormat("yyyy-mm-dd").format(task.getLeaveTime());
					
					buffer.append(" AND t.leaveTime >= '"+ minDate+"'");
			}
			if(bDate!=null){
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(bDate);
				buffer.append(" AND t.leaveTime <= '"+maxDate +"' AND t.leaveTime == null");
				
			}				
			
			hql = buffer.toString();
			
			System.out.println("分页"+hql);
			
			page = this.getTaskService().taskPager(1, hql);
			
			setMessage("成功查找到相关信息！");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
			
		String searchInfo = getParam("searchInfo");
		
		System.out.println("分页"+hql);
		
		page = this.getTaskService().taskPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
	}

	public String add(){
		
		drivers = this.getTaskService().findUseableDriver();
		
		cars = this.getTaskService().findUseableCar();
		
		if(drivers == null || cars == null){
			
			setMessage("没有可用的车辆和驾驶员出车！");
			
			return "search";
			
		}
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0)
			
			return "add";
		
		if(param == 1){
			
				if (task.getCar() == null|| task.getDriver() == null ){
				
				setMessage("请选择车辆和驾驶员！");
				
				return "add";
			}
			
			try{
				
				this.taskService.create(task);
				
				setMessage("成功添加出车表!");
				
				setTask(null);
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("发生错误：" + e.getMessage());
				
				return "add";
			}
		}
		
		setMessage("未知错误，添加失败");
		
		return "add";
	}
	
	public String search(){
		
		try{
			
			if(task == null){
				
				setMessage("搜索条件为空，请重新输入！");
				
				return "search";
			}
			
			this.setTasks(this.getTaskService().searchTask(task));
			
			return "search";
			
		}catch (Exception e){
			
			setMessage("查找任务单发生错误： " + e.getMessage());
			
			e.printStackTrace();
			
			return "search";
		}
	}
	
	public String delete(){


		Integer id = Integer.parseInt(getParam("id"));
		
		if(null == this.taskService.findTaskById(Task.class, id)){
			
			setMessage("不存在ID为" + id + "的任务单！");
			
			return "search";
		}
		
		this.taskService.delete(id);
		
		setMessage("删除成功");
		
		return "search";
	}
	
	public String edit(){


		try {
			
			drivers = this.getTaskService().findUseableDriver();
			
			cars = this.getTaskService().findUseableCar();
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("taskId"));
				
				setTask(this.taskService.findTaskById(Task.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				taskService.update(task);
				
				setMessage("修改成功！");
				
				return "search";
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			setMessage("查找任务单发生错误： " + e.getMessage());
			
			return "edit";
		}
		
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			tasks = this.getTaskService().findAllTask();
			
			if(tasks != null){
				
				return "search";
			}
			
			setMessage("未查询到任务单！");
			
			return "search";
		} catch (Exception e){
			
			setMessage("查找任务单发生错误： " + e.getMessage());
			
			e.printStackTrace();
			 
			return "search";
			}	
	}
	
	protected String getParam(String key){
		
		return ServletActionContext.getRequest().getParameter(key);
	}
	
	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
/*	
	public String test(){
		
		Page<Task> page = new Page<Task>(currentPage);
		
		page = this.getTaskService().test(null, page);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "test";
	}*/

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Integer getsMile() {
		return sMile;
	}

	public void setsMile(Integer sMile) {
		this.sMile = sMile;
	}

}
