package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//TODO Modify localhost
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.model.Listing;
import com.revature.service.ListingService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ListingController {
	@Autowired
	ListingService listingService;
	
	@GetMapping("/listing/**")
	public @ResponseBody Listing  findListingById(Integer id) {
		return listingService.findById(id);		
	}
	
	@GetMapping("/listingAll")
	public @ResponseBody List<Listing>  findAllLists() {
		return listingService.findAllListing();		
	}
		
	@GetMapping("/test/**")
	public String handleGet() {
		return "Success!";
	}
	
		
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleClientError(HttpClientErrorException e) {
		return ResponseEntity
				.status(e.getStatusCode())
				.body(String.format("{\"message\": \"%s\"}", e.getMessage()));
	}

	@PostMapping("/newlisting")
	@ResponseStatus(HttpStatus.CREATED)
	public Listing create(@RequestBody Listing listing) {
		return listingService.create(listing);
	}
	
}
