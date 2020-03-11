package com.revature.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Listing;
import com.revature.model.Template;

public interface TemplateDao extends JpaRepository<Template, Integer>{
	
	Template findByListing(Listing listing);
}
