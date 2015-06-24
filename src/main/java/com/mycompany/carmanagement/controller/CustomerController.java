package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mycompany.carmanagement.service.CustomerService;
import com.mycompany.carmanagement.web.json.bean.CustomerJsonBean;
import com.mycompany.carmanagement.web.json.response.CustomerJsonResponse;
import com.mycompany.carmanagement.web.json.response.CustomerListJsonResponse;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "customer";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public CustomerListJsonResponse getAll(@RequestParam int jtStartIndex,
			@RequestParam int jtPageSize) {
		CustomerListJsonResponse jstr;
		List<CustomerJsonBean> customerList;
		try {
			long customerCount = customerService.getCount();
			customerList = customerService.getAll(jtStartIndex/jtPageSize, jtPageSize);;
			jstr = new CustomerListJsonResponse("OK", customerList,
					customerCount);
		} catch (Exception e) {
			jstr = new CustomerListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public CustomerJsonResponse add(
			@ModelAttribute CustomerJsonBean jsnCustomerBean,
			BindingResult result) {
		CustomerJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new CustomerJsonResponse("ERROR",
					"Form invalid");
		}
		try {
			customerService.add(jsnCustomerBean);
			jsonJtableResponse = new CustomerJsonResponse("OK", jsnCustomerBean);
		} catch (Exception e) {
			jsonJtableResponse = new CustomerJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CustomerJsonResponse update(
			@ModelAttribute CustomerJsonBean customerBean, BindingResult result) {
		CustomerJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new CustomerJsonResponse("ERROR",
					"Form invalid");
		}
		try {
			customerService.update(customerBean);
			jsonJtableResponse = new CustomerJsonResponse("OK", customerBean);
		} catch (Exception e) {
			jsonJtableResponse = new CustomerJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public CustomerJsonResponse remove(@RequestParam String id) {
		CustomerJsonResponse jsonJtableResponse;
		try {
			customerService.delete(new Long(id));
			jsonJtableResponse = new CustomerJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new CustomerJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}
}
