package com.howe.vehicledispatch.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.service.IFixService;
import com.howe.vehicledispatch.util.StringUtil;

@SuppressWarnings("serial")
@Controller
public class FixAciton extends IAction{
	
	@Autowired
	private IFixService fixService;
	private Fix fix;
	private List<Fix> fixs;
	private List<Car> cars;
	private Date lDate;
	private Date bDate;
	private Integer cCost;
	
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
		if ("pager".equals(action))
			
			return pager();
		return "fail";
	}

	private String pager() {
		
		if( pno == null ){
			
			StringBuffer buffer = new StringBuffer(" SELECT  f FROM Fix f WHERE 1=1");
			
			if(!StringUtil.isNull(fix.getId())){
				
				buffer.append(" AND f.id="+ fix.getId());
				
			}
			
			if(!StringUtil.isNull(fix.getCar().getId())){
				
				buffer.append(" AND f.car.id = "+ fix.getCar().getId());
			}
			
			if(!StringUtil.isNull(fix.getLeaveTime())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(fix.getLeaveTime());
				
				buffer.append(" AND f.leaveTime >='"+ minDate+"'");
			}
			
			if(!StringUtil.isNull(lDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(lDate);
				
				buffer.append(" AND f.leaveTime <='"+ maxDate+"'");
			}
			
			if(!StringUtil.isNull(fix.getBackTime())){
	
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(fix.getBackTime());
	
				buffer.append(" AND f.backTime >='"+ minDate+"'");
			}
			if(!StringUtil.isNull(bDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(bDate);
				
				buffer.append(" AND f.backTime <='"+ maxDate+"'");
			}
		
			
			hql = buffer.toString();
			
			page = this.getFixService().fixPager(1, hql);
			
			setMessage("成功查找到相关信息！");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
			
		String searchInfo = getParam("searchInfo");
		
		page = this.getFixService().fixPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
	}

	public String add(){
		
		cars = this.getFixService().findUseableCar();
		
		if( cars == null){
			
			setMessage("没有车辆来维修！");
			return "search";
			
		}
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0)
			
			return "add";
		
		if(param == 1){
			System.out.println(fix.getCar().getId()+":"+fix.getCar().getBrand());
			
				if (fix.getCar() == null){
				
				setMessage("请选择车辆!");
				
				return "add";
			}
			
			try{
				
				this.getFixService().create(fix);
				
				setMessage("成功添加维修信息!");
				
				setFix(null);
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("发生错误：" + e.getMessage());
				
				return "add";
			}
		}
		
		setMessage("未知错误，添加失败！");
		
		return "add";
	}
	
	public String search(){
		
		try{
			
			if(fix == null){
				
				setMessage("搜索条件为空，请重新输入！");
				
				return "search";
			}
			
			fixs = this.getFixService().searchFix(fix);
			
			if(fixs != null){
				
				return "search";
			}
			
			setMessage("查询信息不能为空");
			return "search";
			
		}catch (Exception e){
			
			setMessage("查找维修信息时发生错误： " + e.getMessage());
			
			e.printStackTrace();
			
			return "search";
		}
	}
	
	public String delete(){


		Integer id = Integer.parseInt(getParam("fixId"));
		
		if(null == this.getFixService().findFixById(Fix.class, id)){
			
			setMessage("不存在ID为" + id + "的维修信息！");
			
			return "search";
		}
		
		this.getFixService().delete(id);
		
		setMessage("删除成功!");
		
		return "search";
	}
	
	public String edit(){


		try {
			
			cars = this.getFixService().findUseableCar();
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("fixId"));
				
				setFix(this.getFixService().findFixById(Fix.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				this.getFixService().update(fix);
				
				setMessage("修改成功！");
				
				return "search";
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			setMessage("查找维修单发生错误： " + e.getMessage());
			
			return "search";
		}
		
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			fixs = this.getFixService().findAllFix();
			
			if(fixs != null){
				setMessage("维修信息为空！");
				return "search";
			}
			
			setMessage("未查询到维修信息！");
			
			return "search";
		} catch (Exception e){
			
			setMessage("查找维修信息发生错误： " + e.getMessage());
			
			e.printStackTrace();
			 
			return "search";
			}	
	}
	
	protected String getParam(String key){
		
		return ServletActionContext.getRequest().getParameter(key);
	}

	public IFixService getFixService() {
		return fixService;
	}

	public void setFixService(IFixService fixService) {
		this.fixService = fixService;
	}

	public Fix getFix() {
		return fix;
	}

	public void setFix(Fix fix) {
		this.fix = fix;
	}

	public List<Fix> getFixs() {
		return fixs;
	}

	public void setFixs(List<Fix> fixs) {
		this.fixs = fixs;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

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

	public Integer getcCost() {
		return cCost;
	}

	public void setcCost(Integer cCost) {
		this.cCost = cCost;
	}

}
