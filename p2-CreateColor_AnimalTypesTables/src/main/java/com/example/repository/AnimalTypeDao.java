package com.example.repository;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.AnimalTypes;

@Repository("animalTypeRepo")
@Transactional

public class AnimalTypeDao {
	 @Autowired
	 private SessionFactory sessionFactory;
	  
	  public AnimalTypeDao() {}
	  
	  public void insert(AnimalTypes animalType) {
	  sessionFactory.getCurrentSession().save(animalType); }
	  
	  public void update(AnimalTypes animalType) {
	  sessionFactory.getCurrentSession().update(animalType); }
	  
	  public AnimalTypes selectById(Integer id) { return
	  sessionFactory.getCurrentSession().get(AnimalTypes.class, id); }
	  
	  public List<AnimalTypes> selectAll(){ return
	  sessionFactory.getCurrentSession().createQuery("from AnimalTypes",
	  AnimalTypes.class).list(); }
	 }
