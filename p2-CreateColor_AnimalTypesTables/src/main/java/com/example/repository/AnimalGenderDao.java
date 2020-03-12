package com.example.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.AnimalGender;

@Repository("genderRepo")
@Transactional
public class AnimalGenderDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	//no args constructor
	public AnimalGenderDao() {}
	
	public void insert(AnimalGender animalGender) {
		sessionFactory.getCurrentSession().save(animalGender);
	}
	
	public void update(AnimalGender animalGender) {
		sessionFactory.getCurrentSession().update(animalGender);
	}
	
	public AnimalGender selectById(Integer id) {
		return sessionFactory.getCurrentSession().get(AnimalGender.class, id);	
	}
	
	public List<AnimalGender> selectAll() {
		return sessionFactory.getCurrentSession().createQuery("from AnimalGender", AnimalGender.class).list();
	}
	
	

}
