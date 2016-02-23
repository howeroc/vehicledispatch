package com.howe.vehicledispatch.bean;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "task")
public class Task {
	
	private Integer id;
	
	private Car car;
	
	private Driver driver;
	
	private Date leaveTime;
	
	private Date backTime;
	
	private String dest;
	
	private Integer mile;
	
	private Integer mode;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "id", unique=true, nullable =false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="cId", nullable = false)
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="dId", nullable = false)
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Temporal (TemporalType.DATE)
	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	@Temporal (TemporalType.DATE)
	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Integer getMile() {
		return mile;
	}

	public void setMile(Integer mile) {
		this.mile = mile;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}


}
