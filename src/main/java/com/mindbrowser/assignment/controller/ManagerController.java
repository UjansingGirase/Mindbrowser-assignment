/**
 * 
 */
package com.mindbrowser.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindbrowser.assignment.config.JwtTokenProvider;
import com.mindbrowser.assignment.dto.ApiResponse;
import com.mindbrowser.assignment.dto.JwtAuthenticationResponse;
import com.mindbrowser.assignment.dto.LoginRequest;
import com.mindbrowser.assignment.dto.ManagerRequest;
import com.mindbrowser.assignment.model.Manager;
import com.mindbrowser.assignment.repository.ManagerRepository;
import com.mindbrowser.assignment.service.ManagerService;

/**
 * @author Ujan
 *
 */
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerService managerService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/sign-in")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> createManager(@Valid @RequestBody ManagerRequest managerRequest) {

		if (managerRepository.findByEmail(managerRequest.getEmail()) != null) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		Manager manager = new Manager();
		manager.setEmail(managerRequest.getEmail());
		manager.setAddress(managerRequest.getAddress());
		manager.setFirstName(managerRequest.getFirstName());
		manager.setLastName(managerRequest.getLastName());
		manager.setCompanyName(managerRequest.getCompanyName());
		manager.setDateOfBirth(managerRequest.getDateOfBirth());
		manager.setPassword(passwordEncoder.encode(managerRequest.getPassword()));
		managerRepository.save(manager);
		return new ResponseEntity(new ApiResponse(true, "Manager Registered!"), HttpStatus.OK);
	}

}
