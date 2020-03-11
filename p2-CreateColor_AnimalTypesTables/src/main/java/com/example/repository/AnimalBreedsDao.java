package com.example.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.AnimalBreeds;

@Repository("breedsRepo")
@Transactional

public class AnimalBreedsDao {
	@Autowired
	 private SessionFactory sessionFactory;
	  
	  public AnimalBreedsDao() {}
	  
	  public void insert(AnimalBreeds animalBreed) {
	  sessionFactory.getCurrentSession().save(animalBreed); }
	  
	  public void update(AnimalBreeds animalBreed) {
	  sessionFactory.getCurrentSession().update(animalBreed); }
	  
	  public AnimalBreeds selectById(Integer id) { return
	  sessionFactory.getCurrentSession().get(AnimalBreeds.class, id); }
	  
	  public List<AnimalBreeds> selectAll(){ return
	  sessionFactory.getCurrentSession().createQuery("from AnimalBreeds", AnimalBreeds.class).list(); 
	  }
}

