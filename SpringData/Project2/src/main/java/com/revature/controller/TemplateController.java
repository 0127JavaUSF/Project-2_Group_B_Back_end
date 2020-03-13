package com.revature.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dao.ApplicationDao;
import com.revature.dao.ListingDao;
import com.revature.dao.TemplateDao;
import com.revature.dao.UserDao;
import com.revature.model.Application;
import com.revature.model.Listing;
import com.revature.model.Template;
import com.revature.model.User;
import com.revature.service.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TemplateController {

	private TemplateDao templateDao;

	private HttpServletResponse response;
	
	public TemplateController() {}
	
	@Autowired
	public TemplateController(TemplateDao templateDao, HttpServletResponse response) {
		super();
		this.templateDao = templateDao;
		this.response = response;
	}
	
	@PostMapping(value="/template/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Template> createTemplate(@RequestBody @Valid Template template, @CookieValue(value = "token", defaultValue = "") String token) {

		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		//save userId with template
		User user = new User();
		user.setId(userId);
		template.setUser(user);
		
		Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
		template.setDate(ts);
	    		
		Template createdTemplate = templateDao.save(template);
		
		createdTemplate.setUser(null); //do not return user
		
		return ResponseEntity
				.status(201)
				.body(createdTemplate);
	}
	
	@GetMapping(value="/template.app", produces="application/json", params= {"listing_id"})
	public ResponseEntity<Template> findTemplateByListingId(int listing_id) {
		
		Listing listing = new Listing();
		listing.setId(listing_id);
				
		Template template = templateDao.findByListing(listing);
		template.setUser(null); //do not return user
		
		return ResponseEntity.status(200).body(template);
	}
}
