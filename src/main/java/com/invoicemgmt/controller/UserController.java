package com.invoicemgmt.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.invoicemgmt.model.User;
import com.invoicemgmt.repository.UserRepository;


@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/sign-up")
	public void postUser(@RequestBody User user) {
		String code = user.getUsername();			// user.username + ":" + user.passoword
		String username =  new String(Base64.getDecoder().decode(code)).split(":")[0];
		String password =  new String(Base64.getDecoder().decode(code)).split(":")[1];
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(username + "--" + password);
		String encPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		userRepository.save(user);
	}
	
	@GetMapping("/login")
	public Principal login(Principal principal) {
		if(principal.getName() == null)
			throw new Error("Invalid Credentials");
		return principal;
	}
}
