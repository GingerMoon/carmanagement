package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.respository.CarRepository;

@Service
public class CarService {

	public static final String NA = "NA";
	
    @Autowired
    CarRepository repositoryCar;
    
    public void save(Car car) {
    	if(car.getName().isEmpty()) {
    		car.setName(NA);
    	}
    	if(car.getDescription().isEmpty()) {
    		car.setDescription(NA);
    	}
    	this.repositoryCar.save(car);
    }
    
    public void delete(Car car) {
    	List<Car> cars = find(car);
    	for(Car i : cars) {
    		this.repositoryCar.delete(i);
		}
    }

    public List<Car> find(Car car) {
    	long id = car.getId();
    	String name = car.getName().isEmpty()? "%" : car.getName();
    	String description = car.getDescription().isEmpty()? "%" : car.getDescription();
    	
    	List<Car> cars = null;
    	if(id == -1) {
    		cars = this.repositoryCar.findWithoutID(name, description);
    	}
    	else {
    		Car e = this.repositoryCar.findById(id);
    		cars = new ArrayList<Car> ();
    		cars.add(e);
    	}
    	return cars;
    }
    
	public List<Car> findAll() {
		return (List<Car>) this.repositoryCar.findAll();
	}
}
