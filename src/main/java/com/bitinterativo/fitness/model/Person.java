package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@MappedSuperclass
public abstract class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String sex;
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date birthDate; 
	
	private String userName;
	private String password;
	private String type;
	private String status;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="person_role", joinColumns = @JoinColumn(name = "person_id", 
										referencedColumnName = "id", 
										table = "person"),
									inverseJoinColumns = @JoinColumn(name="role_id",
										referencedColumnName = "id",
										table = "role"))
	private List<Role> roles;
	
	public Person() {
		
	}
	
	public Person(String name, String sex, String userName, String password, String type, String status) {
		this.name = name;
		this.sex = sex;
		this.userName = userName;
		this.password = password;
		this.type = type;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}
}
