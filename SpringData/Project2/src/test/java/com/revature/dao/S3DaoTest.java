package com.revature.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class S3DaoTest {
	S3Dao dao;
	
	@Before
	public void setUp() throws Exception {
		 dao = new S3Dao();
	}

	 @Test 
	 public void setReceiptTest () { assertTrue(dao.setFileInS3("reimbId_UserId.jpg")==1); }
	 
	@Test
	public void getFileFromS3Test() {assertTrue(dao.getFileFromS3("reimbId_UserId.jpg")==1);}	
	  

}
