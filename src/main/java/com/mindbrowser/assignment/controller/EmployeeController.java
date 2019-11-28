/**
 * 
 */

package com.mindbrowser.assignment.controller;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindbrowser.assignment.dto.ApiResponse;
import com.mindbrowser.assignment.dto.EmployeeRequest;
import com.mindbrowser.assignment.dto.EmployeeResponse;
import com.mindbrowser.assignment.model.Employee;
import com.mindbrowser.assignment.model.Manager;
import com.mindbrowser.assignment.repository.EmployeeRepository;
import com.mindbrowser.assignment.service.ManagerService;

/**
 * @author Ujan
 *
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ManagerService managerService;

	@PostMapping("/add-employee")
	public ResponseEntity<?> createEmployee(@AuthenticationPrincipal UsernamePasswordAuthenticationToken currentUser,
			@Valid @RequestBody EmployeeRequest employeeRequest) {
		Manager mngr = (Manager) currentUser.getPrincipal();
		Manager manager = managerService.findManagerByEmail(mngr.getEmail());
		Employee emp = new Employee();
		emp.setFirstName(employeeRequest.getFirstName());
		emp.setLastName(employeeRequest.getLastName());
		emp.setAddress(employeeRequest.getAddress());
		emp.setCity(employeeRequest.getCity());
		emp.setMobile(employeeRequest.getMobile());
		emp.setDateOfBirth(employeeRequest.getDateOfBirth());
		emp.setManager(manager);
		employeeRepository.save(emp);
		return new ResponseEntity(new ApiResponse(true, "Employee added successfully"), HttpStatus.OK);
	}

	@PutMapping("/update-employee/{employeeId}")
	public ResponseEntity<String> updateEmployee(
			@AuthenticationPrincipal UsernamePasswordAuthenticationToken currentUser, @PathVariable Long employeeId,
			@Valid @RequestBody EmployeeRequest employeeRequest) {
		Manager mngr = (Manager) currentUser.getPrincipal();
		Employee employee = employeeRepository.findByEmployeeIdAndManager(employeeId, mngr);
		if (employee == null) {
			return new ResponseEntity(new ApiResponse(false, "Employee not found"), HttpStatus.NOT_FOUND);
		}
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setAddress(employeeRequest.getAddress());
		employee.setCity(employeeRequest.getCity());
		employee.setMobile(employeeRequest.getMobile());
		employee.setDateOfBirth(employeeRequest.getDateOfBirth());
		employee.setManager(mngr);
		employeeRepository.save(employee);
		return new ResponseEntity(new ApiResponse(true, "Employee updated successfully"), HttpStatus.OK);
	}

	@DeleteMapping("/delete-employee/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal UsernamePasswordAuthenticationToken currentUser,
			@PathVariable Long employeeId) {
		Manager mngr = (Manager) currentUser.getPrincipal();
		Employee employee = employeeRepository.findByEmployeeIdAndManager(employeeId, mngr);
		if (employee == null) {
			return new ResponseEntity(new ApiResponse(false, "Employee not found"), HttpStatus.NOT_FOUND);
		}
		employeeRepository.delete(employee);
		return new ResponseEntity(new ApiResponse(true, "Employee deleted successfully"), HttpStatus.OK);
	}

	@GetMapping("/getall-employess")
	public ResponseEntity<?> getAllEmpoyeeByManager(
			@AuthenticationPrincipal UsernamePasswordAuthenticationToken currentUser) {
		Manager mngr = (Manager) currentUser.getPrincipal();
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<EmployeeResponse>>() {
		}.getType();
		List<EmployeeResponse> employees = modelMapper.map(employeeRepository.findByManager(mngr), listType);
		return new ResponseEntity<List<EmployeeResponse>>(employees, HttpStatus.OK);
	}

}
