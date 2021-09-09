package com.assignment2_advance_web_dev.springbootbackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment2_advance_web_dev.springbootbackend.exception.ResourceNotFoundException;
import com.assignment2_advance_web_dev.springbootbackend.model.Employee;
import com.assignment2_advance_web_dev.springbootbackend.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployee() {
		
		// get all employees
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
		//save employee
		return employeeRepository.save(employee);
	}

	public ResponseEntity<Employee> getEmployeeBtId(long id) {
		// get Employee By id
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employeee not found with id:"+id));
		return ResponseEntity.ok(employee);
	}

	public ResponseEntity<Employee> updateEmployee(long id, Employee employeeDetails) {
		// update employee
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employeee not found with id:"+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	public ResponseEntity<Map<String, Boolean>> deleteEmployee(long id) {
		// check existense of employee
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employeee not found with id:"+id));
		//delete employee
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap();
		response.put("Deleted", Boolean.TRUE);	
		return ResponseEntity.ok(response);
	}

	
}
