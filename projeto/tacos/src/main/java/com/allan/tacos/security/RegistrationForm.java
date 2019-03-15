package com.allan.tacos.security;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.allan.tacos.model.User;

import lombok.Data;

@Data
public class RegistrationForm {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(username, passwordEncoder.encode(password), 
				fullname, street, zip, city, state, phone);
	}
}
