package com.howe.vehicledispatch.bean;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "refuel")
public class Refuel {
	
	private Integer id;
	
	private Car car;
	
	private Date reDate;
	
	private Integer quan;
	
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
	public Date getReDate() {
		return reDate;
	}

	public void setReDate(Date reDate) {
		this.reDate = reDate;
	}

	public Integer getQuan() {
		return quan;
	}

	public void setQuan(Integer quan) {
		this.quan = quan;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
