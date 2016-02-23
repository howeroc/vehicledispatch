package com.howe.vehicledispatch.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.service.IDriverService;
import com.howe.vehicledispatch.util.StringUtil;

@Controller
@SuppressWarnings("serial")
public class DriverAction extends IAction {
	
	@Autowired
	private IDriverService driverService;
	private Driver driver;
	private String searchText;
	private List<Driver> driverList;
	private Date bDate;
	private Date dDate;
	private Date wDate;
    
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
		if("pager".equals(action))
			
			return pager();
		
		return "fail";
	}

	private String pager() {
		
		if(driver == null && bDate == null&& dDate == null&& wDate == null && pno==null){
			
			hql = "FROM Driver";
			
			page = this.getDriverService().driverPager(1, hql);
			
			setMessage("搜索条件为空，默认显示所有信息！");
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			ServletActionContext.getRequest().setAttribute("searchInfo",hql);
			
			return "search";
			
		} else
			
		if( pno == null ){
			
			StringBuffer buffer = new StringBuffer(" SELECT  d FROM Driver d WHERE 1=1");
			
			if(!StringUtil.isNull(driver.getId())){
				
				buffer.append(" AND d.id ="+ driver.getId());
				
			}
			
			if(!StringUtil.isNull(driver.getName())){
				
				buffer.append(" AND d.name LIKE  '%"+ driver.getName()+"%'");
			}
			
			if(!StringUtil.isNull(driver.getGender())){
				
				buffer.append(" AND d.name ="+ driver.getGender());
			}
			
			if(!StringUtil.isNull(driver.getBirthdate())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(driver.getBirthdate());
				
				buffer.append(" AND d.birthdate >='"+ minDate +"'");
			}
			if(!StringUtil.isNull(bDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(bDate);
				
				buffer.append(" AND d.birthdate <='"+ maxDate +"'");
			}
			
			if(!StringUtil.isNull(driver.getIden())){
				
				buffer.append(" AND d.iden = "+ driver.getIden());
			}
			
			if(!StringUtil.isNull(driver.getLicenType())){
				
				buffer.append(" AND d.iden = '"+ driver.getLicenType()+"'");
			}
			if(!StringUtil.isNull(driver.getDriveYear())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(driver.getDriveYear());
				
				buffer.append(" AND d.driveYear >= '"+ minDate+"'");
			}
			if(!StringUtil.isNull(dDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(dDate);
				
				buffer.append(" AND d.driveYear <= '"+ maxDate+"'");
			}
			
			if(!StringUtil.isNull(driver.getWorkdate())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(driver.getWorkdate());
				
				buffer.append(" AND d.workdate >='"+ minDate+"'");
			}
			if(!StringUtil.isNull(wDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(wDate);
				
				buffer.append(" AND d.workdate >='"+ maxDate+"'");
			}			
			
			if(!StringUtil.isNull(driver.getPhone())){
				
				buffer.append(" AND d.phone LIKE '%"+ driver.getPhone() +"%'");
			}
			
			if(!StringUtil.isNull(driver.getMode())){
				
				buffer.append(" AND d.mode = "+driver.getMode());
			}
			
			hql = buffer.toString();
			
			page = this.getDriverService().driverPager(1, hql);
			
			setMessage("成功查找到相关信息！");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
			
		String searchInfo = getParam("searchInfo");
		
		page = this.getDriverService().driverPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
	}

	public String add(){
		
		setTitle("添加驾驶员信息");
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0){
			
			return "add";
		}
		
		if(param == 1){
			
			if (StringUtil.isNull(driver.getName()) ||StringUtil.isNull(driver.getIden()) ){
				
				setMessage("请填写必填信息");
				
				return "add";
			}
	
			try {
				
				if(this.driverService.findDriverByName(driver.getName()) !=null){
					
					setMessage("司机\""+driver.getName()+"\"已存在");
					
					return "add";
					
				}
				
				this.driverService.create(driver);
	
				setMessage("成功添加驾驶员\""+driver.getName()+"\"!");
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("发生错误：" + e.getMessage());
				
				return "fail";
			}
		}
		
		setMessage("未知错误，添加失败");
		
		return "add";
	}
	
	public String search(){
		
		setTitle("查找驾驶员信息");
		
		searchText = getParam("id");
		
		if(!StringUtil.isNull(searchText)){

			try{
				
				Integer id = Integer.parseInt(searchText);
				
				driver = this.driverService.findDriverById(Driver.class, id);
				
				if(driver == null){
					
					setMessage("没有查找到ID为\""+id+"\"的驾驶员！");
					
					return "search";
				}
				
				return "search";
				
			} catch (Exception e){
				
				setMessage("按ID查找管理员信息发生错误： " + e.getMessage());
				
				e.printStackTrace();
				
					return "fail";
				}
			
		}
		
		searchText = getParam("name");
		
		if(!StringUtil.isNull(searchText)){
			
			try{
				
				setDriver(this.driverService.findDriverByName(searchText));
				
				if(driver == null){
					
					setMessage("没有查找到姓名为\""+searchText+"\"的驾驶员！");
					
					return "search";
				}

			} catch (Exception e){
				
				setMessage("按姓名查找驾驶员信息发生错误： " + e.getMessage());
				
				e.printStackTrace();
				
				return "fail";
			}
			
			return "search";
		}

		setMessage("搜索信息不能为空！");
		
		return "search";
	}
	
	public String edit(){
		
		setTitle("修改驾驶员信息");
		
		try {
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("driverId"));
				
				setDriver(this.driverService.findDriverById(Driver.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				driverService.update(driver);
				
				setMessage("修改成功！");
				
				return "search";
			}
		} catch (Exception e) {
			
			setMessage("发生错误："+ e.getMessage());
			e.printStackTrace();
			
			return "edit";
		}
		
		return "search";
	}
	
	public String delete(){
		
		Integer id = Integer.parseInt(getParam("driverId"));
		
		if(null == this.driverService.findDriverById(Driver.class, id)){
			
			setMessage("不存在ID为" + id + "的驾驶员信息！");
			
			return "search";
		}
		
		this.driverService.delete(id);
		
		setMessage("删除成功");
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			driverList = this.driverService.findAllDriver();
			
		} catch (Exception e){
			
			setMessage("查找驾驶员信息发生错误： " + e.getMessage());
			
			e.printStackTrace();
			 
			return "fail";
			}
			
		if (driverList == null){
			
			setMessage("管理员表为空");
			
			return "search";
			
		} else{
			
			setMessage("成功查找到管理员信息列表");
		}
		
		return "search";		
	}
	
	protected String getParam(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}

	public IDriverService getDriverService() {
		return driverService;
	}

	public void setDriverService(IDriverService driverService) {
		this.driverService = driverService;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Driver> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Date getdDate() {
		return dDate;
	}

	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}

	public Date getwDate() {
		return wDate;
	}

	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}

}