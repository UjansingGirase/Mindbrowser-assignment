package com.mindbrowser.assignment.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Ujan
 *
 */
public class LoginRequest {

	@NotBlank
	String email;
	@NotBlank
	String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
