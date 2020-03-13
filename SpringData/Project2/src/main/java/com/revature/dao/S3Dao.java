package com.revature.dao;

import java.io.File;

import org.apache.log4j.Logger;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Dao {
	
	Logger logger = Logger.getRootLogger();
	
	public int setFileInS3(String fileId) {
		int status = -1;
		
		try {
			System.setProperty(SDKGlobalConfiguration.ENABLE_S3_SIGV4_SYSTEM_PROPERTY, "true");
			AWSCredentials credentials = new BasicAWSCredentials(System.getenv("S3_KEY_ID"), System.getenv("S3_KEY_ACCESS"));
			AmazonS3 s3client = new AmazonS3Client(credentials);
			String bucketName = "project2s3";
			String uploadFolder = System.getProperty("user.dir")+"/src/main/resources/file_upload/";
			logger.debug("uploadFolder: "+uploadFolder);
			s3client.putObject(new PutObjectRequest(bucketName, fileId, new File(uploadFolder+fileId)));
			status = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
}
	
	public int getFileFromS3(String fileId) {
		/*
		 * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/AmazonS3.html#getObject-com.amazonaws.services.s3.model.GetObjectRequest-
		 * Use the data from the input stream in Amazon S3 object as soon as possible
		 * Read all data from the stream (use GetObjectRequest.setRange(long, long) to request only the bytes you need) 
		 * Close the input stream in Amazon S3 object as soon as possible
		 */
		int status = -1;
		String uploadFolder = System.getProperty("user.dir")+"/src/main/resources/file_download/";
		
		try {
			
			AWSCredentials credentials = new BasicAWSCredentials(System.getenv("S3_KEY_ID"), System.getenv("S3_KEY_ACCESS"));
			AmazonS3 s3client = new AmazonS3Client(credentials);
			String bucketName = "project2s3";
			logger.debug("uploadFolder: "+uploadFolder);
			s3client.getObject(new GetObjectRequest(bucketName, fileId), new File(uploadFolder+fileId));
			
			status = 1;
				
		
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		return status;
	}

}