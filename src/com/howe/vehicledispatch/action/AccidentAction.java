package com.howe.vehicledispatch.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.howe.vehicledispatch.bean.Accident;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.service.IAccidentService;

@SuppressWarnings("serial")
@Controller
public class AccidentAction extends IAction{
	
	@Autowired
	private IAccidentService accidentService;
	private Accident accident;
	private List<Accident> accidents;
	private List<Driver> drivers;
	private List<Car> cars;
	private Date endDate;
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
		// TODO Auto-generated method stub
		return null;
	}

	public String add(){
		
		drivers = this.getAccidentService().findAllDriver();
		
		cars = this.getAccidentService().findAllCar();
		
		if(drivers == null && cars == null){
			
			setMessage("û�п��õĳ����ͼ�ʻԱ��");
			
			return "fail";			
		}
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0)
			
			return "add";
		
		if(param == 1){
			
				if (accident.getCar() == null && accident.getDriver() == null ){
				
				setMessage("��ѡ�����ͼ�ʻԱ��");
				
				return "add";
			}
			
			try{
				
				this.getAccidentService().create(accident);
				
				setMessage("�ɹ�����¹���Ϣ!");
				
				setAccident(null);
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("��������" + e.getMessage());
				
				return "add";
			}
		}
		
		setMessage("δ֪�������ʧ��");
		
		return "add";
	}
	
	public String search(){
		
		try{
			
			if(accident == null && endDate == null){
				
				setMessage("��������Ϊ�գ����������룡");
				
				return "search";
			}
			
			accidents = this.getAccidentService().searchAccident(accident, endDate);
			
			if(accidents != null){
				
				setMessage("δ�����������Ϣ��");
				
				return "search";
			}
			
			return "search";
			
		}catch (Exception e){
			
			setMessage("�������񵥷������� " + e.getMessage());
			
			e.printStackTrace();
			
			return "search";
		}
	}
	
	public String delete(){


		Integer id = Integer.parseInt(getParam("id"));
		
		if(null == this.getAccidentService().findAccidentById(Accident.class, id)){
			
			setMessage("������IDΪ" + id + "���¹���Ϣ��");
			
			return "search";
		}
		
		this.getAccidentService().delete(id);
		
		setMessage("ɾ���ɹ�!");
		
		return "search";
	}
	
	public String edit(){

		try {
			
			drivers = this.getAccidentService().findAllDriver();
			
			cars = this.getAccidentService().findAllCar();
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("id"));
				
				setAccident(this.getAccidentService().findAccidentById(Accident.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				this.getAccidentService().update(accident);
				
				setMessage("�޸ĳɹ���");
				
				return "search";
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			setMessage("�������񵥷������� " + e.getMessage());
			
			return "edit";
		}
		
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			accidents = this.getAccidentService().findAllAccident();
			
			if(accidents != null){
				
				return "search";
			}
			
			setMessage("δ��ѯ�������Ϣ��");
			
			return "search";
		} catch (Exception e){
			
			setMessage("������Ϣ�������� " + e.getMessage());
			
			e.printStackTrace();
			 
			return "search";
			}	
	}
	
	protected String getParam(String key){
		
		return ServletActionContext.getRequest().getParameter(key);
	}

	public IAccidentService getAccidentService() {
		return accidentService;
	}

	public void setAccidentService(IAccidentService accidentService) {
		this.accidentService = accidentService;
	}

	public Accident getAccident() {
		return accident;
	}

	public void setAccident(Accident accident) {
		this.accident = accident;
	}

	public List<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getcCost() {
		return cCost;
	}

	public void setcCost(Integer cCost) {
		this.cCost = cCost;
	}

}
