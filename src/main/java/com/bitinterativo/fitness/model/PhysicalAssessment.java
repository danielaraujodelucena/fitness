package com.bitinterativo.fitness.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class PhysicalAssessment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@SuppressWarnings("deprecation")
	@ForeignKey(name = "id_client")
	@ManyToOne
	private Client client;
	
	@SuppressWarnings("deprecation")
	@ForeignKey(name = "id_personal_training")
	@ManyToOne
	private PersonalTraining personalTraining;

	private Long physical_test_id;
	
	public PhysicalAssessment(Date date, Client client, PersonalTraining personalTraining, Long physical_test_id) {
		this.date = date;
		this.client = client;
		this.personalTraining = personalTraining;
		this.physical_test_id = physical_test_id;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public PersonalTraining getPersonalTraining() {
		return personalTraining;
	}

	public void setPersonalTraining(PersonalTraining personalTraining) {
		this.personalTraining = personalTraining;
	}

	public Long getPhysical_test_id() {
		return physical_test_id;
	}

	public void setPhysical_test_id(Long physical_test_id) {
		this.physical_test_id = physical_test_id;
	}
}
