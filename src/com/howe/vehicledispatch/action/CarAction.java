package com.howe.vehicledispatch.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.service.ICarService;
import com.howe.vehicledispatch.util.StringUtil;

@Controller
@SuppressWarnings("serial")
public class CarAction extends IAction {
	
	@Autowired
	private ICarService carService;
	private Car car;
	private String searchText;
	private List<Car> carList;
	private Integer maxNum;
	private Date maxDate;
    
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

		if(car == null && maxNum == null&& maxDate == null && pno==null){
			
			hql = "FROM Car";
			
			page = this.getCarService().carPager(1, hql);
			
			setMessage("搜索条件为空，默认显示所有信息！");
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			ServletActionContext.getRequest().setAttribute("searchInfo",hql);
			
			return "search";
			
		} else
			
		if( pno == null ){
			
			StringBuffer buffer = new StringBuffer(" SELECT  c FROM Car c WHERE 1=1");
			
			if(!StringUtil.isNull(car.getId())){
				
				buffer.append(" AND c.id="+ car.getId());
				
			}
			
			if(!StringUtil.isNull(car.getType())){
				
				buffer.append(" AND c.type = '"+ car.getType()+"'");
			}
			
			if(!StringUtil.isNull(car.getLicenId())){
				
				buffer.append(" AND c.licenId = '"+ car.getLicenId()+"'");
			}
			
			if(!StringUtil.isNull(car.getSeatNum())){
				
				buffer.append(" AND c.seatNum >= "+ car.getSeatNum());
			}
			
			if(!StringUtil.isNull(maxNum)){
				
				buffer.append(" AND c.seatNum <= "+ maxNum);
			}
			
			if(!StringUtil.isNull(car.getBrand())){
				
				buffer.append(" AND c.brand = '"+ car.getBrand()+"'");
			}
			
			if(!StringUtil.isNull(car.getBuyDate())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(car.getBuyDate());
				
				buffer.append(" AND c.buyDate >= '"+ minDate+"'");
			}
			
			if(!StringUtil.isNull(maxDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(this.maxDate);
				
				buffer.append(" AND c.buyDate <= '"+maxDate+"'");
			}
			
			hql = buffer.toString();
			
			page = this.getCarService().carPager(1, hql);
			
			setMessage("成功查找到相关信息！");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
			
		String searchInfo = getParam("searchInfo");
		
		page = this.getCarService().carPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
	}

	public String add(){
		
		setTitle("添加车辆信息");
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if (param == 0){
			
			return "add";
		}
		
		if (param == 1){
		
			if (StringUtil.isNull(car.getLicenId()) ){
				
				setMessage("请填写必填信息");
				
				return "add";
			}
	
			try {
				
				if(this.carService.findCarByLicen(car.getLicenId()) !=null){
					
					setMessage("车牌号\""+car.getLicenId()+"\"已存在！");
					
					return "add";
					
				}
				
				this.carService.create(car);
	
				setMessage("成功添加车牌号为\""+car.getLicenId()+"\"的车辆!");
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("发生错误：" + e.getMessage());
				
				return "fail";
			}
	
		}
		
		setMessage("未知错误");
		
		return "add";

	}
	
	public String search(){
		
		setTitle("查找车辆信息");
		
		searchText = getParam("id");
		
		if(!StringUtil.isNull(searchText)){

			try{
				
				Integer id = Integer.parseInt(searchText);
				
				car = this.carService.findCarById(Car.class, id);
				
				if(car == null){
					
					setMessage("没有查找到ID为\""+id+"\"的车辆！");
					
					return "search";
				}
				
				return "search";
				
			} catch (Exception e){
				
				setMessage("按ID查找管理员信息发生错误： " + e.getMessage());
				
				e.printStackTrace();
				
					return "fail";
				}
			
		}
		
		searchText = getParam("licenId");
		
		if(!StringUtil.isNull(searchText)){
			
			try{
				
				setCar(this.carService.findCarByLicen(searchText));
				
				if(car == null){
					
					setMessage("没有查找到车牌号为\""+searchText+"\"的车辆！");
					
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
		
		setTitle("修改车辆信息");
		
		try {
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer carId = Integer.parseInt(getParam("carId"));
				
				setCar(this.carService.findCarById(Car.class, carId));
				
				return "edit";
				
			}
			
			if(param == 1){
				
				this.carService.update(car);
				
				setMessage("修改成功！");
				
				return "search";
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "edit";
		}
		
		return "search";
	}
	
	public String delete(){
		
		Integer carId = Integer.parseInt(getParam("carId"));
		
		if(null == this.carService.findCarById(Car.class, carId)){
			
			setMessage("不存在ID为" + carId + "的车辆信息！");
			
			return "search";
		}
		
		this.carService.delete(carId);
		
		setMessage("删除成功");
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			carList = this.carService.findAllCar();
			
		} catch (Exception e){
			
			setMessage("查找驾驶员信息发生错误： " + e.getMessage());
			
			e.printStackTrace();
			
			return "fail";
			
			}
			
		if (carList == null){
			
			setMessage("车辆表为空！");
			
			return "search";
			
		} else{
			
			setMessage("成功查找到车辆信息列表！");
		}
		
		return "search";
	}
	
	protected String getParam(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}

	public ICarService getCarService() {
		return carService;
	}

	public void setCarService(ICarService carService) {
		this.carService = carService;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
}