package com.mycompany.carmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.respository.CarRepository;
import com.mycompany.carmanagement.respository.ProviderRepository;

@Service
public class ProviderService {
	
	public static final String NA = "NA";
	@Autowired
    ProviderRepository repositoryProvider;
    
    public void save(Provider provider) {
    	if(provider.getName().isEmpty()) {
    		provider.setName(NA);
    	}
    	if(provider.getDescription().isEmpty()) {
    		provider.setDescription(NA);
    	}
    	this.repositoryProvider.save(provider);
    }
    
    public void delete(Provider provider) {
    	List<Provider> providers = find(provider);
    	for(Provider i : providers) {
    		this.repositoryProvider.delete(i);
		}
    }

    public List<Provider> find(Provider provider) {
    	long id = provider.getId();
    	String name = provider.getName().isEmpty()? "%" : provider.getName();
    	String description = provider.getDescription().isEmpty()? "%" : provider.getDescription();
    	
    	List<Provider> providers = null;
    	if(id == -1) {
    		providers = this.repositoryProvider.findWithoutID(name, description);
    	}
    	else {
    		Provider e = this.repositoryProvider.findById(id);
    		providers = new ArrayList<Provider> ();
    		providers.add(e);
    	}
    	return providers;
    }
    
	public List<Provider> findAll() {
		return (List<Provider>) this.repositoryProvider.findAll();
	}
}
