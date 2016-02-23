package com.howe.vehicledispatch.bean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "car")
public class Car {
	
	private Integer id;

	private String type;								//车辆类型

	private String licenId;								//车牌号
	
	private Integer seatNum;							//座位数
	
	private String brand;								//车辆品牌
	
	private Date buyDate;								//购买日期
	
	private Integer mode;								//车辆状态
	
	private List<Task> tasks = new ArrayList<Task>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id", unique=true, nullable =false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column (name = "type", nullable =false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLicenId() {
		return licenId;
	}

	public void setLicenId(String licenId) {
		this.licenId = licenId;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Temporal (TemporalType.DATE)
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column (name = "mode", nullable =false)
	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	@OneToMany(mappedBy = "car")
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
