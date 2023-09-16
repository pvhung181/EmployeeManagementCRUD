package com.tutorial.springsecurity.demosecurity.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "birth")
	private Date birth;
	@Column(name = "bank_account")
	private String bankAccount;
	
	@Column(name = "date_start_work")
	private Date dateStartWork;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employeeDetails")
	private Employee employee;
	
	public EmployeeDetails() {}

	public EmployeeDetails(Date birth, String bankAccount, Date dateStartWork) {
		this.birth = birth;
		this.bankAccount = bankAccount;
		this.dateStartWork = dateStartWork;
	}

	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Date getDateStartWork() {
		return dateStartWork;
	}

	public void setDateStartWork(Date dateStartWork) {
		this.dateStartWork = dateStartWork;
	}
	
	
}
