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
public class ApplicationController {

	private ApplicationDao appDao;

	private TemplateDao templateDao;
	
	public ApplicationController() {}
	
	@Autowired
	public ApplicationController(ApplicationDao appDao, TemplateDao templateDao) {
		super();
		this.appDao = appDao;
		this.templateDao = templateDao;
	}
	
	@PostMapping(value="/application/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Application> createApplication(@RequestBody @Valid Application app, @CookieValue(value = "token", defaultValue = "") String token) {
		
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}

		app.setUser(new User());
		app.getUser().setId(userId);
		
		Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
		app.setDate(ts);
	    		
		Application createdApp = appDao.save(app);
		
		createdApp.setUser(null); //do not return user
		
		return ResponseEntity
				.status(201)
				.body(createdApp);
	}

	@GetMapping(value="/application/by-listing.app", produces="application/json", params= {"listing_id"})
	public ResponseEntity<List<Application>> findAllApplicationsByListing(int listing_id, @CookieValue(value = "token", defaultValue = "") String token) {
		
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		Listing listing = new Listing();
		listing.setId(listing_id);
		
		Template template = templateDao.findByListing(listing);
		if(template == null)
			return null;
		
		//if user who is logged in is different that user who created the listing and template
		if(template.getListing().getUser().getId() != userId) {
			
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
		
		List<Application> applications = appDao.findAllByTemplate(template);
		
		for(Application app : applications) {
			
			app.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(app.getDate()) );
		}
				
		return ResponseEntity.status(200).body(applications);
	}
}
