package com.revature.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.service.ListingService;

//can we make this a bean? we need the listingService ... getting error "no tests found with test runner junit5"
@Component
class ListingTest {

	@Autowired
	ListingService listingService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Test
	public void findListingByIdTest() {
		
		assertNotNull(listingService.findListingById(40));
	}
	
	@Test
	public void findListingByTypeAndCityTest() {
		
		assertNotNull(listingService.findListingByTypeAndCity(0, 0, null, null));
	}
}
