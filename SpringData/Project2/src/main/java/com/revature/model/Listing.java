package com.revature.model;

import java.sql.Timestamp;
import java.util.List;
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
public class Listing {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp date;
	
	@Transient
	private String dateString;
	
	@Column
    private String about;
	@Column
    private Float age;
    @Column
    private String city;
    @Column
    private Integer color;
    @Column
    private Integer fixed;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="listing_id")	
    private Set<ImageUrl> imageUrls;
    
    @Transient
    private List<String> imagePresignedUrls;
    
    @Column
    private String name;
    @Column
    private Integer sex;
    @Column
    private String species;
    @Column
    private String state;
	
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="type_id", nullable=false)	
    private Integer type;
    */
    
    @Column
    private Integer type;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="listing_id")	
    private Set<VideoUrl> videoUrls;
    
    @Column
    private Float weight;
    @Column
    private String zipCode;
    
    public Listing() {}

	public Listing(Integer id, User user, Timestamp date, String about, Float age, String city, Integer color,
			Integer fixed, Set<ImageUrl> imageUrls, String name, Integer sex, String species, String state,
			Integer type, Set<VideoUrl> videoUrls, Float weight, String zipCode) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.about = about;
		this.age = age;
		this.city = city;
		this.color = color;
		this.fixed = fixed;
		this.imageUrls = imageUrls;
		this.name = name;
		this.sex = sex;
		this.species = species;
		this.state = state;
		this.type = type;
		this.videoUrls = videoUrls;
		this.weight = weight;
		this.zipCode = zipCode;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getFixed() {
		return fixed;
	}

	public void setFixed(Integer fixed) {
		this.fixed = fixed;
	}

	public Set<ImageUrl> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(Set<ImageUrl> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<String> getImagePresignedUrls() {
		return imagePresignedUrls;
	}

	public void setImagePresignedUrls(List<String> imagePresignedUrls) {
		this.imagePresignedUrls = imagePresignedUrls;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set<VideoUrl> getVideoUrls() {
		return videoUrls;
	}

	public void setVideoUrls(Set<VideoUrl> videoUrls) {
		this.videoUrls = videoUrls;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
