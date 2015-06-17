package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.CustomerRepository;
import com.mycompany.carmanagement.respository.EmployeeRepository;

@Service
public class EmployeeService {
	
	public static final String NA = "NA";
	@Autowired
    EmployeeRepository repositoryEmployee;
    
    public void save(Employee employee) {
    	if(employee.getName().isEmpty()) {
    		employee.setName(NA);
    	}
    	if(employee.getDescription().isEmpty()) {
    		employee.setDescription(NA);
    	}
    	this.repositoryEmployee.save(employee);
    }
    
    public void delete(Employee employee) {
    	List<Employee> employees = find(employee);
    	for(Employee i : employees) {
    		this.repositoryEmployee.delete(i);
		}
    }

    public List<Employee> find(Employee employee) {
    	long id = employee.getId();
    	String name = employee.getName().isEmpty()? "%" : employee.getName();
    	String description = employee.getDescription().isEmpty()? "%" : employee.getDescription();
    	
    	List<Employee> employees = null;
    	if(id == -1) {
    		employees = this.repositoryEmployee.findWithoutID(name, description);
    	}
    	else {
    		Employee e = this.repositoryEmployee.findById(id);
    		employees = new ArrayList<Employee> ();
    		employees.add(e);
    	}
    	return employees;
    }
    
	public List<Employee> findAll() {
		return (List<Employee>) this.repositoryEmployee.findAll();
	}
}
