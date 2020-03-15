package com.revature.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dao.ApplicationDao;
import com.revature.dao.TemplateDao;
import com.revature.model.Application;
import com.revature.model.Listing;
import com.revature.model.Template;
import com.revature.model.User;
import com.revature.service.UserService;

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
	
	@GetMapping(value="/application/by-user.app", produces="application/json")
	public ResponseEntity<List<Application>> findAllApplicationsByUser(@CookieValue(value = "token", defaultValue = "") String token) {
	
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		User user = new User();
		user.setId(userId);

		List<Application> apps = appDao.findAllByUser(user);
		//set date
		for(Application app : apps) {
			app.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(app.getDate()) );
			
			//make sure not lazily loaded
			app.getTemplate().getListing().getAbout();
		}
		
		return ResponseEntity
				.status(200)
				.body(apps);
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
		if(template.getUser().getId() != userId) {
			
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
		
		List<Application> applications = appDao.findAllByTemplate(template);
		
		for(Application app : applications) {
			
			app.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(app.getDate()) );
		}
				
		return ResponseEntity.status(200).body(applications);
	}
	
	@PutMapping(value="/application/update-status.app", consumes="application/json", produces="application/json")
	@Transactional
	public ResponseEntity<List<Application>> updateApplicationStatus(@RequestBody @Valid ArrayList<Application> apps, @CookieValue(value = "token", defaultValue = "") String token) {
		
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		List<Application> updated = new ArrayList<Application>();
		for(Application app : apps) { //for all applications

			//get from DB
			Optional<Application> fromDB = appDao.findById(app.getId());
			if(fromDB.isPresent()) { //if found
			
				fromDB.get().setStatus(app.getStatus()); //update status
				appDao.save(fromDB.get()); //save
				updated.add(fromDB.get());
			}			
		}
				
		return ResponseEntity.status(200).body(updated);
	}
}
