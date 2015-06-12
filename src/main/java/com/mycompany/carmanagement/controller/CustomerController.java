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
import com.mycompany.carmanagement.respository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository repositoryCustomer;
    
    @ModelAttribute("customer")
    public Customer customer() {
    	return new Customer("","");
    }
    
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public String addEmployee(@ModelAttribute Customer customer, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("addcustomer") == 0) {
        	this.repositoryCustomer.save(customer);
    	} else if(action.compareTo("delcustomer") == 0) {
    		List<Customer> customers = repositoryCustomer.findByName(customer.getName());
    		for(Customer i : customers) {
        		this.repositoryCustomer.delete(i);
    		}
    	}
    	return "redirect:/customer";
    }
    
    @ModelAttribute("customers")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Customer> getAllcustomer() {
    	return (List<Customer>) repositoryCustomer.findAll();
    }

}
