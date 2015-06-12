package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.respository.SalesRecordRepository;

@Controller
@RequestMapping("/salesRecord")
public class SalesRecordController {

    @Autowired
    SalesRecordRepository repositorySalesRecord;
    
    @ModelAttribute("salesRecord")
    public SalesRecord salesRecord() {
    	return new SalesRecord();
    }
    
    @RequestMapping(value = "/add", method = { RequestMethod.POST, RequestMethod.PUT })
    public String addSalesRecord(@ModelAttribute SalesRecord salesRecord, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("addsalesRecord") == 0) {
        	this.repositorySalesRecord.save(salesRecord);
    	} else if(action.compareTo("delsalesRecord") == 0) {
//    		List<SalesRecord> salesRecords = repositorySalesRecord.findByName(salesRecord.getName());
//    		for(SalesRecord i : salesRecords) {
//        		this.repositorySalesRecord.delete(i);
//    		}
    	}
    	return "redirect:/salesRecord";
    }
    
    
    @ModelAttribute("salesRecords")
    @RequestMapping(method = { RequestMethod.GET })
    public List<SalesRecord> getAllsalesRecord() {
    	return (List<SalesRecord>) repositorySalesRecord.findAll();
    }

}
