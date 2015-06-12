package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.carmanagement.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);
}
