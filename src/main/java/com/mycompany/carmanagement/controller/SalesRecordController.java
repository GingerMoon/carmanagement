package com.mycompany.carmanagement.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.respository.SalesRecordRepository;
import com.mycompany.carmanagement.service.SalesRecordService;

@Controller
@RequestMapping("/salesRecord")
public class SalesRecordController {

	private static DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
	
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
    		String beginDate = dateFormat.format(salesRecord.getBeginDate());
    		String endDate = dateFormat.format(salesRecord.getEndDate());
    		return "redirect:/salesRecord?id=" + salesRecord.getId() + "&car.id=" + salesRecord.getCar().getId() + "&customer.id=" + salesRecord.getCustomer().getId() + "&employee.id=" + salesRecord.getEmployee().getId() + "&beginDate=" + dateFormat.format(salesRecord.getBeginDate()) + " &endDate=" + dateFormat.format(salesRecord.getEndDate()) + "&price=" + salesRecord.getPrice() + "&description=" + salesRecord.getDescription();
    	} 
    	return "redirect:/salesRecord/all";
    }
    
    @ModelAttribute("salesRecords")
    @RequestMapping(method = { RequestMethod.GET })
    public List<SalesRecord> getSalesrecords(@ModelAttribute SalesRecord salesRecord) {
    	return (List<SalesRecord>) this.serviceSalesrecord.find(salesRecord);
    }

    @ModelAttribute("salesRecords")
    @RequestMapping(value = "/all", method = { RequestMethod.GET })
    public ModelAndView getSalesrecords() {
    	ModelAndView mav = new ModelAndView("salesRecord");
    	List<SalesRecord> salesRecords = this.serviceSalesrecord.findAll();
    	mav.addObject("salesRecords", salesRecords);
    	return mav;
    }
}
