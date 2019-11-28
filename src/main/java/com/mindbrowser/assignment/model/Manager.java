/**
 * 
 */
package com.mindbrowser.assignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Ujan
 *
 */
@Entity
@Table(name = "manager")
public class Manager {

	@Id
	@Column(name = "MANAGER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long managerId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String address;
	private Date dateOfBirth;
	private String companyName;

	@OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
	private List<Employee> employess = new ArrayList<Employee>();

	public Manager() {
		super();
	}

	public Manager(Long managerId, String email, String firstName, String lastName, String password, String address,
			Date dateOfBirth, String companyName, List<Employee> employess) {
		super();
		this.managerId = managerId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.companyName = companyName;
		this.employess = employess;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Employee> getEmployess() {
		return employess;
	}

	public void setEmployess(List<Employee> employess) {
		this.employess = employess;
	}

	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", address=" + address + ", companyName=" + companyName + ", employess=" + employess + "]";
	}

}
