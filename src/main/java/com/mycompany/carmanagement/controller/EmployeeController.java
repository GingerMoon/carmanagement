package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService serviceEmployee;
    
    @ModelAttribute("employee")
    public Employee employee() {
    	return new Employee("","");
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute Employee employee, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.serviceEmployee.save(employee);
    	} else if(action.compareTo("delete") == 0) {
    		this.serviceEmployee.delete(employee);
    	} else if(action.compareTo("query") == 0) {
    		return "redirect:/employee?id=" + employee.getId() + "&name=" + employee.getName() + "&description=" + employee.getDescription();
    	} 
    	return "redirect:/employee";
    }
    
    @ModelAttribute("employees")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Employee> getEmployees(@ModelAttribute Employee employee) {
    	return (List<Employee>) this.serviceEmployee.find(employee);
    }
    
}
