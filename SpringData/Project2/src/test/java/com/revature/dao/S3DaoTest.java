package com.revature.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class S3DaoTest {
	S3Dao dao = new S3Dao();
	
	@Before
	public void setUp() throws Exception {
		  
	}

	 @Test 
	 public void setReceiptTest () { assertTrue(dao.setFileInS3("AustralianCattleDog_JimHutchins.jpg")==1); }
	 
	@Test
	public void getFileFromS3Test() {assertTrue(dao.getFileFromS3("AustralianCattleDog_JimHutchins.jpg")==1);}	
	  

}
