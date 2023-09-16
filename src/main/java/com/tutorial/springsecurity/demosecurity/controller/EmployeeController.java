package com.tutorial.springsecurity.demosecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.springsecurity.demosecurity.entity.Employee;
import com.tutorial.springsecurity.demosecurity.entity.EmployeeDetails;
import com.tutorial.springsecurity.demosecurity.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(value = {"/", "/employees/list"})
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "list-employees";
	}
	
	@GetMapping("/employees/FormForAdd")
	public String addForm(Model model) {
		Employee em = new Employee();
		EmployeeDetails details = new EmployeeDetails();
		em.setEmployeeDetails(details);
		model.addAttribute("employee", new Employee());
		return "saveEmployeeForm";
	}
	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
	
	@PostMapping("/employees/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return "saveEmployeeForm";
//		employee.addAddress(new Address(12, "Nguyen Khang","Cau giay","Ha Noi","Viet Nam"));
//		employee.addAddress(new Address(98, "Dong vinh","Dong Hung","Thai binh","Viet Nam"));
		employeeService.save(employee);
		return "redirect:/employees/list";
	}
	
	@PostMapping("/employees/formForUpdate")
	public String updateForm(@RequestParam("employeeId") int theId, Model model) {
		Employee em = employeeService.findById(theId);
		model.addAttribute("employee", em);
		return "saveEmployeeForm";
	}
	
	@GetMapping("/employees/showDetails/{id}") 
	public String showDetail(@PathVariable int id ,Model model) {
		Employee em = employeeService.findById(id);
		model.addAttribute("employeeDetails", em);
		return "employee_details";
	}
}























