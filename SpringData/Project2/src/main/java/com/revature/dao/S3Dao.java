package com.revature.dao;

import java.io.File;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Dao {

	Logger logger = Logger.getRootLogger();

	public int setFileInS3(String fileId) {
		int status = -1;

		try {
			System.setProperty(SDKGlobalConfiguration.ENABLE_S3_SIGV4_SYSTEM_PROPERTY, "true"); //$NON-NLS-1$
			AWSCredentials credentials = new BasicAWSCredentials(System.getenv("S3_KEY_ID"), System.getenv("S3_KEY_ACCESS")); //$NON-NLS-1$ //$NON-NLS-2$
			AmazonS3 s3client = new AmazonS3Client(credentials);
			String bucketName = Messages.getString("S3Dao.0"); //$NON-NLS-1$
			String uploadFolder = System.getProperty("user.dir")+"/src/main/resources/file_upload/"; //$NON-NLS-1$ //$NON-NLS-2$
			logger.debug("uploadFolder: "+uploadFolder); //$NON-NLS-1$
			s3client.putObject(new PutObjectRequest(bucketName, fileId, new File(uploadFolder+fileId)));
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public URL getPresignedURL(String fileId) {
		URL presignedURL;

		AWSCredentials credentials = new BasicAWSCredentials(System.getenv("S3_KEY_ID"), System.getenv("S3_KEY_ACCESS")); //$NON-NLS-1$ //$NON-NLS-2$
		AmazonS3 s3client = new AmazonS3Client(credentials);
		String bucketName = Messages.getString("S3Dao.0"); //$NON-NLS-1$
		String objectKey = fileId;
		Date expiration = new Date();
		long expTimeMilliseconds = expiration.getTime();
		expTimeMilliseconds += 10000; //10 secs
		//we can not make it 24 hours for security reasons
//		expTimeMilliseconds += 1000 * 60 * 60 * 24; // Setting the expiration time at 24h
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(new Date(expTimeMilliseconds));
		presignedURL = s3client.generatePresignedUrl(generatePresignedUrlRequest);		
		logger.debug("PresignedURL: "+presignedURL.toString()); //$NON-NLS-1$
		return presignedURL;
	}

	public int getFileFromS3(String fileId) {
		/*
		 * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/
		 * s3/AmazonS3.html#getObject-com.amazonaws.services.s3.model.GetObjectRequest-
		 * Use the data from the input stream in Amazon S3 object as soon as possible
		 * Read all data from the stream (use GetObjectRequest.setRange(long, long) to
		 * request only the bytes you need) Close the input stream in Amazon S3 object
		 * as soon as possible
		 */
		int status = -1;
		String uploadFolder = System.getProperty("user.dir")+"/src/main/resources/file_download/"; //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			
			AWSCredentials credentials = new BasicAWSCredentials(System.getenv("S3_KEY_ID"), System.getenv("S3_KEY_ACCESS")); //$NON-NLS-1$ //$NON-NLS-2$
			AmazonS3 s3client = new AmazonS3Client(credentials);
			String bucketName = Messages.getString("S3Dao.0"); //$NON-NLS-1$
			logger.debug("uploadFolder: "+uploadFolder); //$NON-NLS-1$
			s3client.getObject(new GetObjectRequest(bucketName, fileId), new File(uploadFolder+fileId));
			
			status = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
