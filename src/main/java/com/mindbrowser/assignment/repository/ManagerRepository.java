package com.mindbrowser.assignment.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindbrowser.assignment.model.Manager;

/**
 * @author Ujan
 *
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	public Manager findByEmail(String email);

	public Manager findByManagerId(Long managerId);

}
