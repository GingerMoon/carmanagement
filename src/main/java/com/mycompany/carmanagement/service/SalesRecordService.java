package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Customer;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.CustomerRepository;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.respository.SalesRecordRepository;

@Service
public class SalesRecordService {
	
	public static final String NA = "NA";
	@Autowired
    SalesRecordRepository repositorySalesRecord;
    
    public void save(SalesRecord salesRecord) {
    	if(salesRecord.getDescription().isEmpty()) {
    		salesRecord.setDescription(NA);
    	}
    	
    	this.repositorySalesRecord.save(salesRecord);
    }
    
    public void delete(SalesRecord salesRecord) {
    	List<SalesRecord> salesRecords = find(salesRecord);
    	for(SalesRecord i : salesRecords) {
    		this.repositorySalesRecord.delete(i);
		}
    }

    public List<SalesRecord> find(SalesRecord salesRecord) {
    	long id = salesRecord.getId(); 
    	String description = salesRecord.getDescription().isEmpty()? "%" : salesRecord.getDescription();
    	
    	List<SalesRecord> salesRecords = null;
    	if(id == -1) {
    		salesRecords = this.repositorySalesRecord.findWithoutID(salesRecord.getCar().getId(), salesRecord.getCustomer().getId(), salesRecord.getEmployee().getId());
    	}
    	else {
    		SalesRecord e = this.repositorySalesRecord.findById(id);
    		salesRecords = new ArrayList<SalesRecord> ();
    		salesRecords.add(e);
    	}
    	return salesRecords;
    }
    
	public List<SalesRecord> findAll() {
		return (List<SalesRecord>) this.repositorySalesRecord.findAll();
	}
	
}
