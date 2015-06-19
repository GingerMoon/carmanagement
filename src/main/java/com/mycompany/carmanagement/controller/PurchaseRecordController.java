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

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.service.PurchaseRecordService;

@Controller
@RequestMapping("/purchaseRecord")
public class PurchaseRecordController {

	private static DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
	
    @Autowired
    PurchaseRecordService servicePurchaserecord;
    
    @ModelAttribute("purchaseRecord")
    public PurchaseRecord purchaseRecord() {
    	return new PurchaseRecord();
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute PurchaseRecord purchaseRecord, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.servicePurchaserecord.save(purchaseRecord);
    	} else if(action.compareTo("delete") == 0) {
    		this.servicePurchaserecord.delete(purchaseRecord);
    	} else if(action.compareTo("query") == 0) {
    		String beginDate = dateFormat.format(purchaseRecord.getBeginDate());
    		String endDate = dateFormat.format(purchaseRecord.getEndDate());
    		return "redirect:/purchaseRecord?id=" + purchaseRecord.getId() + "&car.id=" + purchaseRecord.getCar().getId() + "&customer.id=" + purchaseRecord.getCustomer().getId() + "&employee.id=" + purchaseRecord.getEmployee().getId() + "&beginDate=" + dateFormat.format(purchaseRecord.getBeginDate()) + " &endDate=" + dateFormat.format(purchaseRecord.getEndDate()) + "&price=" + purchaseRecord.getPrice() + "&description=" + purchaseRecord.getDescription();
    	} 
    	return "redirect:/purchaseRecord?all=true";
    }
    
    @ModelAttribute("purchaseRecords")
    @RequestMapping(method = { RequestMethod.GET })
    public List<PurchaseRecord> getPurchaserecords(@ModelAttribute PurchaseRecord purchaseRecord, WebRequest request) {
    	String all = request.getParameter("all");
    	if(all != null) {
    		return (List<PurchaseRecord>) this.servicePurchaserecord.findAll();
    	} else {
    		return (List<PurchaseRecord>) this.servicePurchaserecord.find(purchaseRecord);
    	}
    }
}
