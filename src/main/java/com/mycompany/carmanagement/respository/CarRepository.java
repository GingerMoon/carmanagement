package com.mycompany.carmanagement.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByName(String name);
}
