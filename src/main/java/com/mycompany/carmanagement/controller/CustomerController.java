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
import com.mycompany.carmanagement.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService serviceCustomer;
    
    @ModelAttribute("customer")
    public Customer customer() {
    	return new Customer("","");
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute Customer customer, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.serviceCustomer.save(customer);
    	} else if(action.compareTo("delete") == 0) {
    		this.serviceCustomer.delete(customer);
    	} else if(action.compareTo("query") == 0) {
    		return "redirect:/customer?id=" + customer.getId() + "&name=" + customer.getName() + "&description=" + customer.getDescription();
    	} 
    	return "redirect:/customer";
    }
    
    @ModelAttribute("customers")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Customer> getCustomers(@ModelAttribute Customer customer) {
    	return (List<Customer>) this.serviceCustomer.find(customer);
    }

}
