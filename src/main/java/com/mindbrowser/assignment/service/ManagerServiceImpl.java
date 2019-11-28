/**
 * 
 */
package com.mindbrowser.assignment.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mindbrowser.assignment.model.Manager;
import com.mindbrowser.assignment.repository.ManagerRepository;

/**
 * @author Ujan
 *
 */
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;

	public ManagerServiceImpl() {

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Manager manager = managerRepository.findByEmail(email);
		if (manager == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(manager.getEmail(), manager.getPassword(), true, // Email verification status
				true, true, true, new ArrayList<>());
	}

	@Override
	public Manager findManagerByEmail(String email) {
		Manager manager = managerRepository.findByEmail(email);
		if (manager == null) {
			throw new UsernameNotFoundException(email);
		}
		return manager;
	}

	@Override
	public Manager findByManagerId(Long managerId) {
		Manager manager = managerRepository.findByManagerId(managerId);
		if (manager == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return manager;
	}

}
