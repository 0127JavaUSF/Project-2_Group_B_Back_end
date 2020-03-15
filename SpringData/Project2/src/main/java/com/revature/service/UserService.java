package com.revature.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserService {

	public static String hashPassword(String password) {
		
		Hasher hasher = BCrypt.withDefaults();
		char[] charArray = password.toCharArray();
		String hashedPassword = hasher.hashToString(4, charArray);
		
		return hashedPassword;
	}
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(System.getenv("P2_JWT")); //test. create secret key
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id) //the id MUST be unique among servers. if we were using different servers, we'd use a different value per server
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .signWith(signatureAlgorithm, signingKey);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(System.getenv("P2_JWT")))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}

	public static int getUserIdFromJWT(String token) {

		//test
//		return 1;
		
		if(token == null || token.isEmpty()) {
			return 0;
		}
		
		Claims claims = UserService.decodeJWT(token);
		if(claims == null) {
			return 0;
		}
		
		int userID = 0;
		try {
			userID = Integer.parseInt( claims.getSubject() );
		}
		catch(Exception e) {
		}
		
		return userID;
	}
}
