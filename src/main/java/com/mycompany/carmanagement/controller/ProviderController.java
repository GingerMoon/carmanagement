package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService serviceProvider;
    
    @ModelAttribute("provider")
    public Provider provider() {
    	return new Provider("","");
    }
    
    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public String post_put(@ModelAttribute Provider provider, WebRequest request) {
    	String action = request.getParameter("action");
    	if(action.compareTo("add") == 0) {
        	this.serviceProvider.save(provider);
    	} else if(action.compareTo("delete") == 0) {
    		this.serviceProvider.delete(provider);
    	} else if(action.compareTo("query") == 0) {
    		return "redirect:/provider?id=" + provider.getId() + "&name=" + provider.getName() + "&description=" + provider.getDescription();
    	} 
    	return "redirect:/provider";
    }
    
    @ModelAttribute("providers")
    @RequestMapping(method = { RequestMethod.GET })
    public List<Provider> getProviders(@ModelAttribute Provider provider) {
    	return (List<Provider>) this.serviceProvider.find(provider);
    }

}
