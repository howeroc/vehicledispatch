package com.howe.vehicledispatch.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Driver;
import com.howe.vehicledispatch.bean.Refuel;
import com.howe.vehicledispatch.service.IRefuelService;
import com.howe.vehicledispatch.util.StringUtil;

@SuppressWarnings("serial")
@Controller
public class RefuelAction extends IAction{
	
	@Autowired
	private IRefuelService refuelService;
	private Refuel refuel;
	private List<Refuel> refuels;
	private List<Driver> drivers;
	private List<Car> cars;
	private Date endDate;
	private Integer maxQuan;
	private Integer maxCost;
	
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
			
			StringBuffer buffer = new StringBuffer(" SELECT  r FROM Refuel r WHERE 1=1");
			
			if(!StringUtil.isNull(refuel.getId())){
				
				buffer.append(" AND r.id="+ refuel.getId());
				
			}
			
			if(!StringUtil.isNull(refuel.getCar().getId())){
				
				buffer.append(" AND r.car.id = "+ refuel.getCar().getId());
			}
			
			if(!StringUtil.isNull(refuel.getReDate())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(refuel.getReDate());
				
				buffer.append(" AND r.reDate >='"+ minDate+"'");
			}
			
			if(!StringUtil.isNull(endDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(endDate);
				
				buffer.append(" AND r.reDate <='"+maxDate+"'");
			}
			
			if(!StringUtil.isNull(refuel.getQuan())){
				
				buffer.append(" AND r.quan >= "+ refuel.getQuan());
			}
			
			if(!StringUtil.isNull(maxQuan)){
				
				buffer.append(" AND r.quan <= "+ maxQuan);
			}
			
			if(!StringUtil.isNull(refuel.getCost())){
				
				
				buffer.append(" AND r.cost >= "+refuel.getCost());
			}
			if(!StringUtil.isNull(maxCost)){
				
				
				buffer.append(" AND r.cost <= "+maxCost);
			}
			
			
			
			hql = buffer.toString();
			
			page = this.getRefuelService().refuelPager(1, hql);
			
			setMessage("�ɹ����ҵ������Ϣ��");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
			
		String searchInfo = getParam("searchInfo");
		
		page = this.getRefuelService().refuelPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
	}

	public String add(){
		
		cars = this.getRefuelService().findAllCar();
		
		if( cars == null){
			
			setMessage("��������û�г�����Ϣ���ܽ��м��Ͳ�����");
			return "search";
		}
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if(param == 0)
			
			return "add";
		
		if(param == 1){
			
				if (refuel.getCar() == null){
				
				setMessage("��ѡ�����ͼ�ʻԱ��");
				
				return "add";
			}
			
			try{
				
				this.getRefuelService().create(refuel);
				
				setMessage("�ɹ���Ӽ�����Ϣ!");
				
				setRefuel(null);
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("��������" + e.getMessage());
				
				return "add";
			}
		}
		
		setMessage("δ֪�������ʧ��!");
		
		return "add";
	}
	
	public String search(){
		
		try{
			
			if(refuel == null){
				
				setMessage("��������Ϊ�գ����������룡");
				
				return "search";
			}
			
			refuels = this.getRefuelService().searchRefuel(refuel, endDate);
			
			if(refuels != null){
				
				setMessage("Ϊ��ѯ��������Ϣ��");
				
				return "search";
			}
			
			setMessage("��ѯ��Ϣ����Ϊ��");
			return "search";
			
		}catch (Exception e){
			
			setMessage("���Ҽ�����Ϣ�������� " + e.getMessage());
			
			e.printStackTrace();
			
			return "search";
		}
	}
	
	public String delete(){


		Integer id = Integer.parseInt(getParam("refuelId"));
		
		if(null == this.getRefuelService().findRefuelById(Refuel.class, id)){
			
			setMessage("������IDΪ" + id + "�����񵥣�");
			
			return "search";
		}
		
		this.getRefuelService().delete(id);
		
		setMessage("ɾ���ɹ�");
		
		return "search";
	}
	
	public String edit(){


		try {
			
			cars = this.getRefuelService().findAllCar();
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer id = Integer.parseInt(getParam("refuelId"));
				
				setRefuel(this.getRefuelService().findRefuelById(Refuel.class, id));
				
				return "edit";
				
			}else if(param == 1){
				
				this.getRefuelService().update(refuel);
				
				setMessage("�޸ĳɹ���");
				
				return "search";
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			setMessage("�޸ļ�����Ϣ�������� " + e.getMessage());
			
			return "edit";
		}
		
		return "search";
	}
	
	public String listAll(){
		
		try{
			
			refuels = this.getRefuelService().findAllRefuel();
			
			if(refuels != null){
				
				return "search";
			}
			
			setMessage("δ��ѯ��������Ϣ��");
			
			return "search";
		} catch (Exception e){
			
			setMessage("���Ҽ�����Ϣ�������� " + e.getMessage());
			
			e.printStackTrace();
			 
			return "search";
			}	
	}
	
	protected String getParam(String key){
		
		return ServletActionContext.getRequest().getParameter(key);
	}

	public IRefuelService getRefuelService() {
		return refuelService;
	}

	public void setRefuelService(IRefuelService refuelService) {
		this.refuelService = refuelService;
	}

	public Refuel getRefuel() {
		return refuel;
	}

	public void setRefuel(Refuel refuel) {
		this.refuel = refuel;
	}

	public List<Refuel> getRefuels() {
		return refuels;
	}

	public void setRefuels(List<Refuel> refuels) {
		this.refuels = refuels;
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

	public Integer getMaxQuan() {
		return maxQuan;
	}

	public void setMaxQuan(Integer maxQuan) {
		this.maxQuan = maxQuan;
	}

	public Integer getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(Integer maxCost) {
		this.maxCost = maxCost;
	}
}
