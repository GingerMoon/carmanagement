package com.mycompany.carmanagement.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.CarRepository;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarRepository repositoryCar;
    
    @ModelAttribute("car")
    public Car Car() {
    	return new Car("","");
    }
    
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public String addEmployee(@ModelAttribute Car car, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("addcar") == 0) {
        	this.repositoryCar.save(car);
    	} else if(action.compareTo("delcar") == 0) {
    		List<Car> cars = repositoryCar.findByName(car.getName());
    		for(Car i : cars) {
        		this.repositoryCar.delete(i);
    		}
    	}
    	return "redirect:/car";
    }
    
    @ModelAttribute("cars")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Car> getAllCar() {
    	return (List<Car>) repositoryCar.findAll();
    }

}
