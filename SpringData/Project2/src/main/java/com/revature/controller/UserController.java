package com.revature.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.service.UserService;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Verifyer;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
	
	private UserDao userDao;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;

	public UserController() {}
	
	@Autowired
	public UserController(UserDao userDao, HttpServletRequest request, HttpServletResponse response) {
		super();
		this.userDao = userDao;
		this.request = request;
		this.response = response;
	}
	
	@PostMapping(value="/user/login.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> login(@RequestBody User user, @CookieValue(value = "token", defaultValue = "") String token) {
				
		User newUser = userDao.findByUsername(user.getUsername());

		//compared password from client to stored hash in database
		Verifyer verifyer = BCrypt.verifyer();
		if(verifyer.verify(user.getPassword().toCharArray(), newUser.getPassword().toCharArray()).verified)
		{			
			//get session token (don't use because it is stored on server (not stateless))
//			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//			String token = attr.getSessionId();

			//always create a new token if logging in
			//store id in JWT
			token = UserService.createJWT("1", "TeamB", newUser.getId().toString(), 0);
						
			Cookie cookie = new Cookie("token", token);
			cookie.setPath("/");
			response.addCookie(cookie);
		
			//do not return sensitive info to client
			newUser.setId(0);
			newUser.setUsername("");
			newUser.setPassword("");

			return ResponseEntity
					.status(200)
					.body(newUser);
		}

		//return unauthorized error
		return ResponseEntity
				.status(401)
				.body(null);
	}
	
	@GetMapping(value="/user/logout.app")
	public void logout(@CookieValue(value = "token", defaultValue = "") String token) {
		
		Cookie cookies[] = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
			
				if(cookie.getName().equals("token")) {
					
					cookie.setMaxAge(0); //a zero value will cause the cookie to be deleted
					
					response.addCookie(cookie); //update response
					
					break;
				}
			}
		}
	}
}
