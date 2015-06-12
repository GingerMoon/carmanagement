package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.EmployeeRepository;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository repositoryEmployee;
    
    @ModelAttribute("employee")
    public Employee employee() {
    	return new Employee("","");
    }
    
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public String addEmployee(@ModelAttribute Employee employee, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("addemployee") == 0) {
        	this.repositoryEmployee.save(employee);
    	} else if(action.compareTo("delemployee") == 0) {
    		List<Employee> employees = repositoryEmployee.findByName(employee.getName());
    		for(Employee i : employees) {
        		this.repositoryEmployee.delete(i);
    		}
    	}
    	return "redirect:/employee";
    }
    
    
    @ModelAttribute("employees")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Employee> getAllemployee() {
    	return (List<Employee>) repositoryEmployee.findAll();
    }

}
