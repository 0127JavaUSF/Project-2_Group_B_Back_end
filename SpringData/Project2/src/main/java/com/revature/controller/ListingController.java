package com.revature.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//TODO Modify localhost
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.model.Listing;
import com.revature.model.Template;
import com.revature.service.ListingService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ListingController {
	Logger logger = Logger.getRootLogger();
	
	@Autowired
	ListingService listingService;
	
	@GetMapping("/listing/{id}")
	public @ResponseBody Listing  findListingById(@PathVariable Integer id) {
		logger.debug("@PathVariable Integer id: "+id);
		return listingService.findById(id);		
	}
	
	@GetMapping("/listingAll")
	public @ResponseBody List<Listing>  findAllLists() {
		return listingService.findAllListing();		
	}
		
		
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleClientError(HttpClientErrorException e) {
		return ResponseEntity
				.status(e.getStatusCode())
				.body(String.format("{\"message\": \"%s\"}", e.getMessage()));
	}

	@PostMapping(value="/listing/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Listing> createListing(@RequestBody @Valid Listing listing) {
		
		return ResponseEntity
				.status(201)
				.body(listingService.create(listing));
	}
	
	@PostMapping("/newlisting")
	@ResponseStatus(HttpStatus.CREATED)
	public Listing create(@RequestBody Listing listing) {
		return listingService.create(listing);
	}
	
	/* Used to test: successful */
	@GetMapping("/test/**")
	public String handleGet() {
		logger.info("Request mapped with HandleGet");
		return "Success!";
	}
	
}
