package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);
}
