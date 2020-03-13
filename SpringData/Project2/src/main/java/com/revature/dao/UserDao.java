package com.revature.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.User;

public interface UserDao extends JpaRepository<User, String>{
	
	public User findByUsername(String username);

}
