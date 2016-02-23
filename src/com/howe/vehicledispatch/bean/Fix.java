package com.howe.vehicledispatch.bean;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "fix")
public class Fix {
	
	private Integer id;
	
	private Car car;
	
	private Date leaveTime;
	
	private Date backTime;
	
	private Integer cost;


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

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
}
