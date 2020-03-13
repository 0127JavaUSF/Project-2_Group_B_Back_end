package com.revature.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dao.ApplicationDao;
import com.revature.dao.ListingRepository;
import com.revature.dao.TemplateDao;
import com.revature.dao.UserDao;
import com.revature.model.Application;
import com.revature.model.Listing;
import com.revature.model.Template;
import com.revature.model.User;

@Controller
@CrossOrigin(origins="http://localhost:4200") 
public class P2Controller {

	@Autowired
	private ApplicationDao appDao;
	
	@Autowired
	private ListingRepository listingDao;
	
	@Autowired
	private TemplateDao templateDao;

	@Autowired
	private UserDao userDao;
	
	@GetMapping(value="/listing.app", produces="application/json", params= {"id"})
	public @ResponseBody Listing findListingById(int id) {
		
		Optional<Listing> res =  listingDao.findById(id);
		if(res.isPresent() == false)
			return null;
		
		return res.get();
	}
	// call function I just made.
	// return a list using findbyuserid.
	// getting all the listings that match the userId (all the listings made by the same user).
	@GetMapping(value="/listing/find-by-user.app", produces="application/json")
	public @ResponseBody List<Listing> findAllByUser() {
		User user = new User();
		user.setId(1); //test.getuserfromsession
		
		List<Listing> listings =  listingDao.findAllByUser(user);

		return listings;
	}
	
	@GetMapping(value="/listing/search.app", produces="application/json", params= {"page", "type", "city"})
	public @ResponseBody Page<Listing> findListingByTypeAndCity(int page, int type, String city) {
		
		//Pageable pageable = PageRequest.of(0, 8, Sort.by("city").descending());
		Pageable pageable = PageRequest.of(page, 2);
		
		if(type > 0 && city.isEmpty() == false) {
			return listingDao.findByTypeAndCityContainingIgnoreCase(type, city, pageable);
		}
		else if(type > 0) {
			return listingDao.findByType(type, pageable);
		}
		else if(city.isEmpty() == false) {
			return listingDao.findByCityContainingIgnoreCase(city, pageable);
		}
		else {
			return listingDao.findAll(pageable);
		}
	}
	
	@PostMapping(value="/template/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Template> createTemplate(@RequestBody @Valid Template template) {
		
		//test
		template.getUser().setId(1);
		
		Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
		template.setDate(ts);
	    		
		Template createdTemplate = templateDao.save(template);
		
		createdTemplate.setUser(null); //do not return user
		
		return ResponseEntity
				.status(201)
				.body(createdTemplate);
	}
	
	@GetMapping(value="/template.app", produces="application/json", params= {"listing_id"})
	public @ResponseBody Template findTemplateByListingId(int listing_id) {
		
		Listing listing = new Listing();
		listing.setId(listing_id);
				
		Template template = templateDao.findByListing(listing);
		template.setUser(null); //do not return user
		
		return template;
	}
	
	@PostMapping(value="/application/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Application> createApplication(@RequestBody @Valid Application app) {
		
		//test
		app.setUser(new User());
		app.getUser().setId(1);
		
		Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
		app.setDate(ts);
	    		
		Application createdApp = appDao.save(app);
		
		createdApp.setUser(null); //do not return user
		
		return ResponseEntity
				.status(201)
				.body(createdApp);
	}
	
	@GetMapping(value="/application/by-listing.app", produces="application/json", params= {"listing_id"})
	public @ResponseBody List<Application> findAllApplicationsByListing(int listing_id) {
		
		Listing listing = new Listing();
		listing.setId(listing_id);
		
		Template template = templateDao.findByListing(listing);
		if(template == null)
			return null;
		
		int userId = 1; //test
		
		//if user who is logged in is different that user who created the listing and template
		if(template.getListing().getUser().getId() != userId) {
			
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
		
		List<Application> applications = appDao.findAllByTemplate(template);
		
		for(Application app : applications) {
			
			app.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(app.getDate()) );
		}
				
		return applications;
	}
	
	@GetMapping(value="/application/by-user.app", produces="application/json")
	public @ResponseBody List<Application> findByUser() {
		User user = new User();
		user.setId(1);
		//test getUserFromSession
		List<Application> apps = appDao.findAllByUser(user);
		for(Application app : apps) {
			app.setUser(null); //do not send user back to client
			if(app.getTemplate() != null) {
				app.getTemplate().setUser(null);
				if(app.getTemplate().getListing() != null) {
					app.getTemplate().getListing().setUser(null);
				}
			}
		}
		return apps;
	}
}
