package com.revature.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	//qa is a list of the custom questions and answers on the application
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="app_id")
	private List<ApplicationQA> qa;
	
	@Column
    private String firstName;

	@Column
    private String lastName;
	
	@Column
    private String email;
	
	@Column
    private String phone;

	//not sure we should include this for privacy reasons. an applicant can be contacted without their home address
//	@Column
//    private String address;
	
	@Column
    private String city;

	@Column
    private String state;
	
	@Column
    private Integer status;
	
	@Column
    private String zipCode;
	
	public Application() {}

	public Application(Integer id, List<ApplicationQA> qa, String firstName, String lastName, String email,
			String phone, String city, String state, Integer Status, String zipCode) {
		super();
		this.id = id;
		this.qa = qa;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.state = state;
		this.status = Status;
		this.zipCode = zipCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ApplicationQA> getQa() {
		return qa;
	}

	public void setQa(List<ApplicationQA> qa) {
		this.qa = qa;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
