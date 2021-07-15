package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="physicaltest")
public class Pollock7Fold extends PhysicalTest implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	private Double subscapular;
	private Double midAxiliary;
	private Double triceps;
	private Double thigh;
	private Double suprailiac;
	private Double abdomen;
	private Double pectoral;
	
	public Pollock7Fold() {
	
	}
	
	public Pollock7Fold(Date date, Long id_client, Long id_personalTraining, Double weight, Double height, String namePhysicalTest, Double subscapular, Double midAxiliary, Double triceps, Double thigh, Double suprailiac, Double abdomen, Double pectoral) {
		super(date, id_client, id_personalTraining, weight, height, namePhysicalTest);
		this.subscapular = subscapular;
		this.midAxiliary = midAxiliary;
		this.triceps = triceps;
		this.thigh = thigh;
		this.suprailiac = suprailiac;
		this.abdomen = abdomen;
		this.pectoral = pectoral;
	}

	public String getBodyDensity() {
		int age = 18;
		Double result1 = ((getSubscapular() + getMidAxiliary() + getTriceps() + getThigh() + getSuprailiac() + getAbdomen() + getPectoral()) * 0.00043499);
		Double result2 = 0.00000055 * Math.pow(result1, 2);
		Double result3 = 0.0002882 * age;
		Double bodyDensity = 1.11200000 - ((result1 + result2) - result3);
		
		return "Densidade corporal: " + bodyDensity;
	}
	
	public Double getSubscapular() {
		return subscapular;
	}

	public void setSubscapular(Double subscapular) {
		this.subscapular = subscapular;
	}

	public Double getMidAxiliary() {
		return midAxiliary;
	}

	public void setMidAxiliary(Double midAxiliary) {
		this.midAxiliary = midAxiliary;
	}

	public Double getTriceps() {
		return triceps;
	}

	public void setTriceps(Double triceps) {
		this.triceps = triceps;
	}

	public Double getThigh() {
		return thigh;
	}

	public void setThigh(Double thigh) {
		this.thigh = thigh;
	}

	public Double getSuprailiac() {
		return suprailiac;
	}

	public void setSuprailiac(Double suprailiac) {
		this.suprailiac = suprailiac;
	}

	public Double getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(Double abdomen) {
		this.abdomen = abdomen;
	}

	public Double getPectoral() {
		return pectoral;
	}

	public void setPectoral(Double pectoral) {
		this.pectoral = pectoral;
	}
}
