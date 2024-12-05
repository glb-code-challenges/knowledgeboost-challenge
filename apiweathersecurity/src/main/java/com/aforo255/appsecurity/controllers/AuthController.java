package com.aforo255.appsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aforo255.appsecurity.dtos.AuthRequest;
import com.aforo255.appsecurity.dtos.AuthResponse;
import com.aforo255.appsecurity.jwt.JwtToken;
import com.aforo255.appsecurity.service.AuthService;

@RestController
@RequestMapping("/weather")
public class AuthController {

	@Autowired
	AuthService authService;
	@Autowired
	private JwtToken jwtTokenCross;
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
		
	@PostMapping("/auth")
	  public ResponseEntity<?> post(@RequestBody AuthRequest request) throws Exception {
	    logger.info("Post: UserName {} - Password {}", request.getUserName(), request.getPassword());
	    if (!authService.validatedCredentials(request.getUserName(), request.getPassword())) {
	      return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
	    }
	    AuthResponse response = new AuthResponse(jwtTokenCross.generateToken(request), request.getUserName(), "1d");
	    return ResponseEntity.ok(response);
	  }	
	
	
}
