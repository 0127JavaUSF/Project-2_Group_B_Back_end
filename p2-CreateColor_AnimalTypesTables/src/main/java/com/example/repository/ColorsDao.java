package com.example.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.AnimalColors;

/*
 * Spring ORM - is a Spring module that allows Spring to manage
 * an ORM framework in your place. In our case, we let Spring ORM
 * manage Hibernate.
 */

@Repository("colorsRepo")
@Transactional
/*
 * tells Spring that the method or class creates transactions.
 * Spring will then create and manage your sessions and commits for you.
 */
public class ColorsDao {
	
	@Autowired
	private SessionFactory sesFact;
	
	public ColorsDao() {
	}
	
	public void insert(AnimalColors color) {
		sesFact.getCurrentSession().save(color);
	}
	
	public void update(AnimalColors color) {
		sesFact.getCurrentSession().update(color);
	}
	
	public AnimalColors selectById(Integer id) {
		return sesFact.getCurrentSession().get(AnimalColors.class, id);
	}
	
	public List<AnimalColors> selectAll(){
		return sesFact.getCurrentSession().createQuery("from AnimalColors", AnimalColors.class).list();
	}

}
