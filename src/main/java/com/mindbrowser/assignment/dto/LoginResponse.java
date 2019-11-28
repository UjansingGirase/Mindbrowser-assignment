/**
 * 
 */
package com.mindbrowser.assignment.dto;

/**
 * @author Ujan
 *
 */
public class LoginResponse {

	private ServiceStatus status;
	private String message;
	private int code;
	private String token;

	public ServiceStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
