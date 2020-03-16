package com.revature.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.dao.ListingDao;
import com.revature.dao.S3Dao;
import com.revature.model.ImageUrl;
import com.revature.model.Listing;
import com.revature.model.User;

@Service
public class ListingService {
	
	private ListingDao listingDao;
	
	private S3Dao s3;
	
	@Autowired
	public ListingService (ListingDao listingDao, S3Dao s3) {
		super();
		this.listingDao = listingDao;
		this.s3 = s3;
	}
	
	public Listing findListingById(int id) {
		
		Optional<Listing> res =  listingDao.findById(id);
		if(res.isPresent() == false)
			return null;
		
		return res.get();
	}
	
	public ResponseEntity<List<Listing>> findAllByUser(String token) {
				
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		User user = new User();
		user.setId(userId);
		
		List<Listing> listings =  listingDao.findAllByUser(user);
		
		for(Listing list : listings) {
			
			if(list.getDate() != null) {
				list.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(list.getDate()) );
			}
		}

		return ResponseEntity
				.status(200)
				.body(listings);
	}
	
	public Page<Listing> findListingByTypeAndCity(int page, int type, String city, String state) {
		
		//Pageable pageable = PageRequest.of(0, 8, Sort.by("city").descending());
		Pageable pageable = PageRequest.of(page, 8);
		
		if(type > 0 && city.isEmpty() == false && state.isEmpty() == false) {
			return listingDao.findByTypeAndCityContainingIgnoreCaseAndStateContainingIgnoreCase(type, city, state, pageable);
		}
		else if(type > 0 && city.isEmpty() == false) {
			return listingDao.findByTypeAndCityContainingIgnoreCase(type, city, pageable);
		}
		else if(type > 0 && state.isEmpty() == false) {
			return listingDao.findByTypeAndStateContainingIgnoreCase(type, state, pageable);
		}
		else if(city.isEmpty() == false && state.isEmpty() == false) {
			return listingDao.findByCityContainingIgnoreCaseAndStateContainingIgnoreCase(city, state, pageable);
		}

		else if(type > 0) {
			return listingDao.findByType(type, pageable);
		}
		else if(city.isEmpty() == false) {
			return listingDao.findByCityContainingIgnoreCase(city, pageable);
		}
		else if(state.isEmpty() == false) {
			return listingDao.findByStateContainingIgnoreCase(state, pageable);
		}
		else {
			return listingDao.findAll(pageable);
		}
	}
	
	public ResponseEntity<Listing> createListing(Listing listing, String token) {
		
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		//userId on listing object can't be null
		User user = new User();
		user.setId(userId);
		listing.setUser(user);
				
		Listing newListing = listingDao.save(listing);
		
		//get presigned urls
		if(newListing.getImageUrls().size() > 0) { //if images (these are not the actual files)
			
			List<String> presignedUrls = new ArrayList<String>();
		
			//String awsUrl = System.getenv("P2_S3_URL");
			String awsUrl = System.getenv("S3_URL_P2");
			int i = 0;
			for(ImageUrl img : newListing.getImageUrls()) {
				
				//listing id / array index
				String append = newListing.getId().toString() + "/" + Integer.toString(i);
				
				String presignedUrl = s3.getPresignedURL(append).toString();
				
				presignedUrls.add(presignedUrl);
				
				//this is not the presigned url
				//this is the permanent url to the image on the amazon s3 bucket
				img.setUrl(awsUrl + "/" + append);
				
				i++;
			}
			
			//save the updated listing
			newListing.setImagePresignedUrls(presignedUrls);
			
			newListing.setDate(new Timestamp(System.currentTimeMillis()));
			
			newListing = listingDao.save(newListing);		
		}
		
		return ResponseEntity
				.status(201)
				.body(newListing);
	}
}
