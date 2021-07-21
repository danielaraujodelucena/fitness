package com.bitinterativo.fitness.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportPollock7 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String namePersonalTraining;
	private String cref;
	private String phone;
	private String email;
	private String nameAvaliacao;
	private String dateAvaliacao;
	private String nameClient;
	private String sex;
	private String age;
	private Double weight;
	private Double height;
	private Double subscapular;
	private Double triceps;
	private Double pectoral;
	private Double midAxiliary;
	private Double thigh;
	private Double suprailiac;
	private Double abdomen;
	private Double bodyDensity;
	private String fatLevel;
	
	public ReportPollock7() {
	
	}
	
	public ReportPollock7(String namePersonalTraining, String cref, String phone, String email, String nameAvaliacao,
			String dateAvaliacao, String nameClient, String sex, String age, Double weight, Double height,
			Double subscapular, Double triceps, Double pectoral, Double midAxiliary, Double thigh, Double suprailiac,
			Double abdomen, Double bodyDensity, String fatLevel) {
		this.namePersonalTraining = namePersonalTraining;
		this.cref = cref;
		this.phone = phone;
		this.email = email;
		this.nameAvaliacao = nameAvaliacao;
		this.dateAvaliacao = dateAvaliacao;
		this.nameClient = nameClient;
		this.sex = sex;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.subscapular = subscapular;
		this.triceps = triceps;
		this.pectoral = pectoral;
		this.midAxiliary = midAxiliary;
		this.thigh = thigh;
		this.suprailiac = suprailiac;
		this.abdomen = abdomen;
		this.bodyDensity = bodyDensity;
		this.fatLevel = fatLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePersonalTraining() {
		return namePersonalTraining;
	}

	public void setNamePersonalTraining(String namePersonalTraining) {
		this.namePersonalTraining = namePersonalTraining;
	}

	public String getCref() {
		return cref;
	}

	public void setCref(String cref) {
		this.cref = cref;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameAvaliacao() {
		return nameAvaliacao;
	}

	public void setNameAvaliacao(String nameAvaliacao) {
		this.nameAvaliacao = nameAvaliacao;
	}

	public String getDateAvaliacao() {
		return dateAvaliacao;
	}

	public void setDateAvaliacao(String dateAvaliacao) {
		this.dateAvaliacao = dateAvaliacao;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getSubscapular() {
		return subscapular;
	}

	public void setSubscapular(Double subscapular) {
		this.subscapular = subscapular;
	}

	public Double getTriceps() {
		return triceps;
	}

	public void setTriceps(Double triceps) {
		this.triceps = triceps;
	}

	public Double getPectoral() {
		return pectoral;
	}

	public void setPectoral(Double pectoral) {
		this.pectoral = pectoral;
	}

	public Double getMidAxiliary() {
		return midAxiliary;
	}

	public void setMidAxiliary(Double midAxiliary) {
		this.midAxiliary = midAxiliary;
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

	public Double getBodyDensity() {
		return bodyDensity;
	}

	public void setBodyDensity(Double bodyDensity) {
		this.bodyDensity = bodyDensity;
	}

	public String getFatLevel() {
		return fatLevel;
	}

	public void setFatLevel(String fatLevel) {
		this.fatLevel = fatLevel;
	}
	
	public Double resultBodyDensity(int age, String sex) {
		if(age >= 18) {
			if(sex.equals("Masculino")) {
				double result1 = getSubscapular() + getMidAxiliary() + getTriceps() + getThigh() + getSuprailiac() + getAbdomen() + getPectoral();
				double result2 = 0.00043499 * result1;
				double result3 = 0.00000055 * Math.pow(result1, 2);
				double result4 = 0.0002882 * age;
				
				return (1.11200000 - (result2 + result3) - result4);
				
			} else if(sex.equals("FEMININO")) {
				double result1 = this.subscapular + this.midAxiliary + this.triceps + this.thigh + this.suprailiac + this.abdomen + this.pectoral;
				double result2 = 0.00046971 * result1;
				double result3 = 0.00000056 * Math.pow(result1, 2);
				double result4 = 0.00012828 * age;
				
				return (1.0970 - (result2 + result3)) - (result4);
			}
		}	
		
		return null;
	}
	
	public String resultFatLevel(int age, String sex, double bodyDensity){
		double fat = (((double) 4.95 / bodyDensity) - 4.5) * 100;
		
		if(sex.equals("Masculino")) {
			if(age >= 18 && age <= 25) {
				if(fat >= 4 && fat <= 7.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 8 && fat <= 11.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 12 && fat <= 13.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 14 && fat <= 16.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 17 && fat <= 19.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 20 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 26 && fat <= 36) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 26 && age <= 35) {
				if(fat >= 8 && fat <= 11.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 12 && fat <= 15.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 16 && fat <= 17.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 18 && fat <= 21.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 22 && fat <= 24.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 25 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 28 && fat <= 36) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 36 && age <= 45) {				
				if(fat >= 10 && fat <= 15.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 16 && fat <= 18.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 19 && fat <= 20.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 21 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 24 && fat <= 26.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 27 && fat <= 29.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 30 && fat <= 39) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 46 && age <= 55) {
				if(fat >= 12 && fat <= 17.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 18 && fat <= 20.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 21 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 24 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 26 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 28 && fat <= 31.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 32 && fat <= 38) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 56 && age <= 65) {
				if(fat >= 13 && fat <= 19.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 20 && fat <= 21.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 22 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 24 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 26 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 28 && fat <= 31.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 32 && fat <= 38) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
		}
		else if(sex.equals("Feminino")) {
			if(age >= 18 && age <= 25) {
				if(fat >= 13 && fat <= 16.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 17 && fat <= 19.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 20 && fat <= 22.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 23 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 26 && fat <= 28.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 29 && fat <= 32.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 26 && fat <= 36) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 26 && age <= 35) {
				if(fat >= 8 && fat <= 11.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 12 && fat <= 15.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 16 && fat <= 17.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 18 && fat <= 21.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 22 && fat <= 24.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 25 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 28 && fat <= 36) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 36 && age <= 45) {				
				if(fat >= 10 && fat <= 15.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 16 && fat <= 18.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 19 && fat <= 20.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 21 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 24 && fat <= 26.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 27 && fat <= 29.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 30 && fat <= 39) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 46 && age <= 55) {
				if(fat >= 12 && fat <= 17.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 18 && fat <= 20.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 21 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 24 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 26 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 28 && fat <= 31.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 32 && fat <= 38) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
			else if(age >= 56 && age <= 65) {
				if(fat >= 13 && fat <= 19.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está EXCELENTE.";
				}
				else if(fat >= 20 && fat <= 21.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está BOM.";
				}
				else if(fat >= 22 && fat <= 23.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ACIMA DA MÉDIA.";
				}
				else if(fat >= 24 && fat <= 25.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está na MÉDIA.";
				}
				else if(fat >= 26 && fat <= 27.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está ABAIXO DA MÉDIA.";
				}
				else if(fat >= 28 && fat <= 31.99) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está RUIM.";
				}
				else if(fat >= 32 && fat <= 40) {
					return "A densidade corporal é de " + bodyDensity + ", o que significa que o PERCENTUAL DE GORDURA está MUITO RUIM.";
				}
			}
		}
			
		return null;
	}
}
