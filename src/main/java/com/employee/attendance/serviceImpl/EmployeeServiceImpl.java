package com.employee.attendance.serviceImpl;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.attendance.entity.Employee;
import com.employee.attendance.repository.EmployeeRepository;
import com.employee.attendance.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(String employeeName) {
		Employee emp = new Employee();
		emp.setEmployeeName(employeeName);
		employeeRepository.save(emp);
	}

	@Override
	public Optional<Employee> findEmployee(long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee;
	}

	

}
