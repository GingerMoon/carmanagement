package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.respository.PurchaseRecordRepository;

@Service
public class PurchaseRecordService {
	
	public static final String NA = "NA";
	@Autowired
    PurchaseRecordRepository repositoryPurchaseRecord;
    
    public void save(PurchaseRecord purchaseRecord) {
    	if(purchaseRecord.getDescription().isEmpty()) {
    		purchaseRecord.setDescription(NA);
    	}
    	
    	this.repositoryPurchaseRecord.save(purchaseRecord);
    }
    
    public void delete(PurchaseRecord purchaseRecord) {
    	List<PurchaseRecord> purchaseRecords = find(purchaseRecord);
    	for(PurchaseRecord i : purchaseRecords) {
    		this.repositoryPurchaseRecord.delete(i);
		}
    }

    public List<PurchaseRecord> find(PurchaseRecord purchaseRecord) {
    	long id = purchaseRecord.getId(); 
    	String description = purchaseRecord.getDescription().isEmpty()? "%" : purchaseRecord.getDescription();
    	
    	List<PurchaseRecord> purchaseRecords = null;
    	
    	if(id == -1) {
    		if((purchaseRecord.getCar().getId() == -1) && (purchaseRecord.getCustomer().getId() == -1) && (purchaseRecord.getEmployee().getId() == -1)){
    			purchaseRecords = this.repositoryPurchaseRecord.findByTime(purchaseRecord.getBeginDate(), purchaseRecord.getEndDate());
    		}
    		else {
    			if(purchaseRecord.getCar().getId() != -1) {
    				purchaseRecords = this.repositoryPurchaseRecord.findByCarId(purchaseRecord.getCar().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate());
        		}
        		if(purchaseRecord.getCustomer().getId() != -1) {
        			purchaseRecords = this.repositoryPurchaseRecord.findByCustomerId(purchaseRecord.getCustomer().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate());
        		}
        		if(purchaseRecord.getEmployee().getId() != -1) {
        			purchaseRecords = this.repositoryPurchaseRecord.findByEmployeeId(purchaseRecord.getEmployee().getId(), purchaseRecord.getBeginDate(), purchaseRecord.getEndDate());
        		}
    		}
    	}
    	else {
    		PurchaseRecord e = this.repositoryPurchaseRecord.findById(id);
    		purchaseRecords = new ArrayList<PurchaseRecord> ();
    		purchaseRecords.add(e);
    	}
    	return purchaseRecords;
    }
    
	public List<PurchaseRecord> findAll() {
		return (List<PurchaseRecord>) this.repositoryPurchaseRecord.findAll();
	}
	
}
