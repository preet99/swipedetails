package com.employee.attendance.service;

import java.util.Optional;

import com.employee.attendance.entity.Employee;

public interface EmployeeService {

	public void addEmployee(String employeeName);

	public Optional<Employee> findEmployee(long employeeId);
}
