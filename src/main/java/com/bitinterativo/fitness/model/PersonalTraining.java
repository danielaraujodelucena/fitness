package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="person")
public class PersonalTraining extends Person implements Serializable, UserDetails {
	static final long serialVersionUID = 1L;

	private String cref;
	private String especialty;
	private String level;
	
	@OneToMany(mappedBy = "personalTraining")
	private List<PhysicalAssessment> listPhysicalAssessment;
	
	public PersonalTraining() {

	}
	
	public PersonalTraining(String name, String sex, String userName, String password, String cref, String especialty, String type, String status, String level) {
		super(name, sex, userName, password, type, status);
		this.cref = cref;
		this.especialty = especialty;
		this.level = level;
	}
	
	public String getCref() {
		return cref;
	}

	public void setCref(String cref) {
		this.cref = cref;
	}

	public String getEspecialty() {
		return especialty;
	}

	public void setEspecialty(String especialty) {
		this.especialty = especialty;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getRoles();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
