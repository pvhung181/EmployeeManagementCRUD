package com.tutorial.springsecurity.demosecurity.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tutorial.springsecurity.demosecurity.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	EntityManager entityManager;
	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		entityManager.merge(employee);
	}

	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

	@Override
	public Employee findEmployeeById(int id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Employee em = entityManager.find(Employee.class, id);
		entityManager.remove(em);
	}


}
