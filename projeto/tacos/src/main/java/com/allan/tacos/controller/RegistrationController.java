package com.allan.tacos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.allan.tacos.repository.UserRepository;
import com.allan.tacos.security.RegistrationForm;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String registrationForm(RegistrationForm registrationForm) {
		return "user/registration";
	}
	
	@PostMapping
	public String processRegistration(@Valid RegistrationForm form, Errors errors) {
		if(errors.hasErrors()) {
			return "user/registration";
		}
		
		userRepository.save(form.toUser(passwordEncoder));
		return "redirect:/login";
	}
}
