package com.revature.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable=false)
	//@NotNull
	private LocalDate date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	//qa is a list of the custom questions and answers on the application
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="app_id", nullable=false)
	private List<ApplicationQA> qa;
	
	@Column(nullable=false)
    private String firstName;

	@Column
    private String lastName;
	
	@Column
    private String email;
	
	@Column
    private String phone;

	@Column
    private String address;
	
	@Column
    private String city;

	@Column
    private String state;
	
	//0 is not viewed, 1 is viewed
	//default is not viewed
	@Column(columnDefinition = "int default 0")
    private Integer status;
	
	@Column
    private String zipCode;
	
	public Application() {}

	public Application(Integer id, LocalDate date, User user, List<ApplicationQA> qa, String firstName, String lastName,
			String email, String phone, String address, String city, String state, Integer status, String zipCode) {
		super();
		this.id = id;
		this.date = date;
		this.user = user;
		this.qa = qa;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.status = status;
		this.zipCode = zipCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void setStatus(Integer status) {
		this.status = status;
	}
}
