package com.tutorial.springsecurity.demosecurity.service;

import java.util.List;

import com.tutorial.springsecurity.demosecurity.entity.Employee;


public interface EmployeeService {
	List<Employee> findAll();

	Employee findById(int theId);

	void save(Employee theEmployee);

	void deleteById(int theId);
}
