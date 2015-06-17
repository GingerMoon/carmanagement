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
import com.mycompany.carmanagement.service.SalesRecordService;

@Controller
@RequestMapping("/salesRecord")
public class SalesRecordController {

    @Autowired
    SalesRecordService serviceSalesrecord;
    
    @ModelAttribute("salesRecord")
    public SalesRecord salesRecord() {
    	return new SalesRecord();
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute SalesRecord salesRecord, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.serviceSalesrecord.save(salesRecord);
    	} else if(action.compareTo("delete") == 0) {
    		this.serviceSalesrecord.delete(salesRecord);
    	} else if(action.compareTo("query") == 0) {
    		return "redirect:/salesRecord?id=" + salesRecord.getId() + "&car.id=" + salesRecord.getCar().getId() + "&customer.id=" + salesRecord.getCustomer().getId() + "&employee.id=" + salesRecord.getEmployee().getId() + "&beginDate=" + salesRecord.getBeginDate() + " &endDate=" + salesRecord.getEndDate() + "&price=" + salesRecord.getPrice() + "&description=" + salesRecord.getDescription();
    	} 
    	return "redirect:/salesRecord";
    }
    
    @ModelAttribute("salesRecords")
    @RequestMapping(method = { RequestMethod.GET })
    public List<SalesRecord> getSalesrecords(@ModelAttribute SalesRecord salesRecord) {
    	//return (List<Salesrecord>) this.serviceSalesrecord.find(salesRecord);
    	return (List<SalesRecord>) this.serviceSalesrecord.findAll();
    }

}
