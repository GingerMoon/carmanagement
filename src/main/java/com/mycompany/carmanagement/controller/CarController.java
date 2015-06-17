package com.mycompany.carmanagement.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService serviceCar;
    
    @ModelAttribute("car")
    public Car Car() {
    	return new Car("","");
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute Car car, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.serviceCar.save(car);
    	} else if(action.compareTo("delete") == 0) {
    		this.serviceCar.delete(car);
    	} else if(action.compareTo("query") == 0) {
    		return "redirect:/car?id=" + car.getId() + "&name=" + car.getName() + "&description=" + car.getDescription();
    	} 
    	return "redirect:/car";
    }
    
    @ModelAttribute("cars")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Car> getCars(@ModelAttribute Car car) {
    	return (List<Car>) this.serviceCar.find(car);
    }

}
