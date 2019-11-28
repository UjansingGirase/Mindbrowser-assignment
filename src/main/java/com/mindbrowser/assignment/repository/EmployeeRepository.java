/**
 * 
 */
package com.mindbrowser.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindbrowser.assignment.model.Employee;
import com.mindbrowser.assignment.model.Manager;
import java.util.List;

/**
 * @author Ujan
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByManager(Manager manager);

	public Employee findByEmployeeId(Long employeeId);

	public Employee findByEmployeeIdAndManager(Long employeeId, Manager mngr);

}
