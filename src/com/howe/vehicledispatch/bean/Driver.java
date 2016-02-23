package com.howe.vehicledispatch.bean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name= "driver")
public class Driver {
	
	private Integer id;
	
	private String name;
	
	private Integer gender;
	
	private Date birthdate;
	
	private String iden;
	
	private String licenType;
	
	private String licenId;
	
	private Date driveYear;

	private Date workdate;
	
	private String phone;
	
	private Integer mode;
	
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

	@Column (name = "name", nullable =false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Temporal (TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getLicenType() {
		return licenType;
	}

	public void setLicenType(String licenType) {
		this.licenType = licenType;
	}

	public String getLicenId() {
		return licenId;
	}

	public void setLicenId(String licenId) {
		this.licenId = licenId;
	}

	@Temporal (TemporalType.DATE)
	public Date getDriveYear() {
		return driveYear;
	}

	public void setDriveYear(Date driveYear) {
		this.driveYear = driveYear;
	}

	@Temporal (TemporalType.DATE)
	public Date getWorkdate() {
		return workdate;
	}

	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column (name = "mode", nullable = false)
	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}
	
	@OneToMany(mappedBy = "driver")
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
