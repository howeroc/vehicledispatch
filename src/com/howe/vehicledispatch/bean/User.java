package com.howe.vehicledispatch.bean;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	private Integer id;
	
	private String name;
	
	private String password;

	private Integer gender;
	
	private Date birthdate;
	
	private Date workdate;
	
	private String phone;
	
	private Integer type;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Column (name = "type", nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}	
}
