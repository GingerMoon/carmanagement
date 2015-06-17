package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.CustomerRepository;

@Service
public class CustomerService {
	
	public static final String NA = "NA";
	@Autowired
    CustomerRepository repositoryCustomer;
    
    public void save(Customer customer) {
    	if(customer.getName().isEmpty()) {
    		customer.setName(NA);
    	}
    	if(customer.getDescription().isEmpty()) {
    		customer.setDescription(NA);
    	}
    	this.repositoryCustomer.save(customer);
    }
    
    public void delete(Customer customer) {
    	List<Customer> customers = find(customer);
    	for(Customer i : customers) {
    		this.repositoryCustomer.delete(i);
		}
    }

    public List<Customer> find(Customer customer) {
    	long id = customer.getId();
    	String name = customer.getName().isEmpty()? "%" : customer.getName();
    	String description = customer.getDescription().isEmpty()? "%" : customer.getDescription();
    	
    	List<Customer> customers = null;
    	if(id == -1) {
    		customers = this.repositoryCustomer.findWithoutID(name, description);
    	}
    	else {
    		Customer e = this.repositoryCustomer.findById(id);
    		customers = new ArrayList<Customer> ();
    		customers.add(e);
    	}
    	return customers;
    }
    
	public List<Customer> findAll() {
		return (List<Customer>) this.repositoryCustomer.findAll();
	}
}
