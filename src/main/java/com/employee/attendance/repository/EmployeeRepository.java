package com.employee.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.attendance.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
