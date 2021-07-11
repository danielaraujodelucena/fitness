package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="person")
public class Client extends Person implements Serializable, UserDetails {
	static final long serialVersionUID = 1L;

	private String detail;
	private Long idPersonalTraining;
	
	public Client() {

	}
	
	public Client(String name, String sex, String userName, String password, String type, String status, String detail, Long idPersonalTraining) {
		super(name, sex, userName, password, type, status);
		this.detail = detail;
		this.idPersonalTraining = idPersonalTraining;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Long getIdPersonalTraining() {
		return idPersonalTraining;
	}

	public void setIdPersonalTraining(Long idPersonalTraining) {
		this.idPersonalTraining = idPersonalTraining;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return super.getRoles();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return this.getUserName();
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
