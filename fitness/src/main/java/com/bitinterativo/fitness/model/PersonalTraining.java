package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PersonalTraining extends Person implements Serializable {
	static final long serialVersionUID = 1L;

	private String cref;
	private String especialty;
	
	public PersonalTraining(String name, String sex, String cref, String especialty) {
		super(name, sex);
		this.cref = cref;
		this.especialty = especialty;
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
}
