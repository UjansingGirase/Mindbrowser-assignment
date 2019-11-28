package com.mindbrowser.assignment.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mindbrowser.assignment.model.Manager;

/**
 * @author Ujan
 *
 */
public interface ManagerService extends UserDetailsService {

	public Manager findManagerByEmail(String email);

	public Manager findByManagerId(Long managerId);
}
