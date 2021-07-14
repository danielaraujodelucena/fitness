package com.bitinterativo.fitness.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PhysicalTest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double weight;
	private double height;
	private String namePhysicalTest;
	
	public PhysicalTest() {
		
	}
	
	public PhysicalTest(Double weight, Double height, String namePhysicalTest) {
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
