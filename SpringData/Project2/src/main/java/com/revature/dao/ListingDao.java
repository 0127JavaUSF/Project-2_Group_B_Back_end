package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Listing;

public interface ListingDao extends JpaRepository <Listing, Integer>{
	
	public Optional<Listing> findById(Integer Id);
	
	public List<Listing> findAll();
	
	public Listing save(Listing listing);
	

}
