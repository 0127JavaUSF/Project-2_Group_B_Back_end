package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dao.ListingRepository;
import com.revature.model.Listing;

@Service
public class ListingService {
	
	private ListingRepository listingDao;
	
	public Listing create(Listing listing) {
		return this.listingDao.save(listing);
	}
	
	public List<Listing> findAllListing(){
		return this.listingDao.findAll();
	}
	public Listing findById(int id) {
		return listingDao.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Listing with id "+id+" not found"));
	}
	
	@Autowired
	public ListingService (ListingRepository listingDao) {
		super();
		this.listingDao = listingDao;
	}
	
	
	
}
