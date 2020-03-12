package com.revature.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Application;
import com.revature.model.User;


public interface ApplicationDao extends JpaRepository<Application, Integer> {
	
	//must follow specific Spring naming format
		List<Application> findByUser(User user);
		
}
