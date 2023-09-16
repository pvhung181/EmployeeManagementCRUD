package com.tutorial.springsecurity.demosecurity.dao;


import java.util.List;

import com.tutorial.springsecurity.demosecurity.entity.Employee;

public interface EmployeeDAO {
	public void save(Employee employee);
	public List<Employee> findAll();
	public Employee findEmployeeById(int id);
	public void deleteById(int id);
	
}
