package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@MappedSuperclass
public abstract class PhysicalTest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Long id_client;
	private Long id_personalTraining;
	private double weight;
	private double height;
	private String namePhysicalTest;
	
	public PhysicalTest() {
		
	}

	public PhysicalTest(Date date, Long id_client, Long id_personalTraining, double weight, double height, String namePhysicalTest) {
		this.date = date;
		this.id_client = id_client;
		this.id_personalTraining = id_personalTraining;
		this.weight = weight;
		this.height = height;
		this.namePhysicalTest = namePhysicalTest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId_client() {
		return id_client;
	}

	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}

	public Long getId_personalTraining() {
		return id_personalTraining;
	}

	public void setId_personalTraining(Long id_personalTraining) {
		this.id_personalTraining = id_personalTraining;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getNamePhysicalTest() {
		return namePhysicalTest;
	}

	public void setNamePhysicalTest(String namePhysicalTest) {
		this.namePhysicalTest = namePhysicalTest;
	}
}
