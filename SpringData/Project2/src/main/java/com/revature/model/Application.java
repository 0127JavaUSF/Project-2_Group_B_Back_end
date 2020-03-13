package com.revature.model;

import java.sql.Timestamp;
import java.util.Set;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"user"}) //do not return user to client
public class Application {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user; //this is the applicant
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp date;

	@Transient
	private String dateString;

//	@ManyToMany
//	@JoinTable(name="applicants", joinColumns = { @JoinColumn(name="application_id")},
//	inverseJoinColumns = { @JoinColumn(name="user_id") })	
//	private List<User> applicants;
	
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
	
	@Column
    private String zipCode;

	//application template that contains the custom questions
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template_id", nullable=false)
	private Template template;

	//list of the custom questions and answers on the application
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="app_id", nullable=false)
	private Set<ApplicationAnswer> answers;
	
	//0 is not viewed, 1 is viewed
	//default is not viewed
	@Column(columnDefinition = "int default 0")
    private Integer status;
	
	public Application() {}
	

	public Application(Integer id, User user, Timestamp date, String firstName, String lastName,
			String email, String phone, String address, String city, String state, String zipCode, Template template,
			Set<ApplicationAnswer> answers, Integer status) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.template = template;
		this.answers = answers;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Set<ApplicationAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<ApplicationAnswer> answers) {
		this.answers = answers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
