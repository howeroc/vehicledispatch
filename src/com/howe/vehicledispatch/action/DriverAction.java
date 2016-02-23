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
			
			setMessage("��������Ϊ�գ�Ĭ����ʾ������Ϣ��");
			
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
			
			setMessage("�ɹ����ҵ������Ϣ��");
			
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
		
		setTitle("��Ӽ�ʻԱ��Ϣ");
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0){
			
			return "add";
		}
		
		if(param == 1){
			
			if (StringUtil.isNull(driver.getName()) ||StringUtil.isNull(driver.getIden()) ){
				
				setMessage("����д������Ϣ");
				
				return "add";
			}
	
			try {
				
				if(this.driverService.findDriverByName(driver.getName()) !=null){
					
					setMessage("˾��\""+driver.getName()+"\"�Ѵ���");
					
					return "add";
					
				}
				
				this.driverService.create(driver);
	
				setMessage("�ɹ���Ӽ�ʻԱ\""+driver.getName()+"\"!");
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("��������" + e.getMessage());
				
				return "fail";
			}
		}
		
		setMessage("δ֪�������ʧ��");
		
		return "add";
	}
	
	public String search(){
		
		setTitle("���Ҽ�ʻԱ��Ϣ");
		
		searchText = getParam("id");
		
		if(!StringUtil.isNull(searchText)){

			try{
				
				Integer id = Integer.parseInt(searchText);
				
				driver = this.driverService.findDriverById(Driver.class, id);
				
				if(driver == null){
					
					setMessage("û�в��ҵ�IDΪ\""+id+"\"�ļ�ʻԱ��");
					
					return "search";
				}
				
				return "search";
				
			} catch (Exception e){
				
				setMessage("��ID���ҹ���Ա��Ϣ�������� " + e.getMessage());
				
				e.printStackTrace();
				
					return "fail";
				}
			
		}
		
		searchText = getParam("name");
		
		if(!StringUtil.isNull(searchText)){
			
			try{
				
				setDriver(this.driverService.findDriverByName(searchText));
				
				if(driver == null){
					
					setMessage("û�в��ҵ�����Ϊ\""+searchText+"\"�ļ�ʻԱ��");
					
					return "search";
				}

			} catch (Exception e){
				
				setMessage("���������Ҽ�ʻԱ��Ϣ�������� " + e.getMessage());
				
				e.printStackTrace();
				
				return "fail";
			}
			
			return "search";
		}

		setMessage("������Ϣ����Ϊ�գ�");
		
		return "search";
	}
	
	public String edit(){
		
		setTitle("�޸ļ�ʻԱ��Ϣ");
		
		try {
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("driverId"));
				
				setDriver(this.driverService.findDriverById(Driver.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				driverService.update(driver);
				
				setMessage("�޸ĳɹ���");
				
				return "search";
			}
		} catch (Exception e) {
			
			setMessage("��������"+ e.getMessage());
			e.printStackTrace();
			
			return "edit";
		}
		
		return "search";
	}
	
	public String delete(){
		
		Integer id = Integer.parseInt(getParam("driverId"));
		
		if(null == this.driverService.findDriverById(Driver.class, id)){
			
			setMessage("������IDΪ" + id + "�ļ�ʻԱ��Ϣ��");
			
			return "search";
		}
		
		this.driverService.delete(id);
		
		setMessage("ɾ���ɹ�");
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			driverList = this.driverService.findAllDriver();
			
		} catch (Exception e){
			
			setMessage("���Ҽ�ʻԱ��Ϣ�������� " + e.getMessage());
			
			e.printStackTrace();
			 
			return "fail";
			}
			
		if (driverList == null){
			
			setMessage("����Ա��Ϊ��");
			
			return "search";
			
		} else{
			
			setMessage("�ɹ����ҵ�����Ա��Ϣ�б�");
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