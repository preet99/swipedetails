package com.employee.attendance.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.attendance.entity.Employee;
import com.employee.attendance.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/addEmployee")
	public String addEmployee(@RequestParam("employeeName") String employeeName )
	{
		employeeService.addEmployee(employeeName );
		//messageProducer.sendMessage("my_topic", "I am all right");
		return "added employee";
	}
	
	@GetMapping("/findById")
	public Optional<Employee> findEmployee(@RequestParam long employeeId)
	{
		Optional<Employee> employee =employeeService.findEmployee(employeeId);
		return employee;
		
	}
}
