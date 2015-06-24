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
import org.springframework.web.context.request.WebRequest;

import com.mycompany.carmanagement.domain.Car;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.domain.Employee;
import com.mycompany.carmanagement.respository.EmployeeRepository;
import com.mycompany.carmanagement.service.EmployeeService;
import com.mycompany.carmanagement.service.EmployeeService;
import com.mycompany.carmanagement.web.json.bean.EmployeeJsonBean;
import com.mycompany.carmanagement.web.json.response.EmployeeJsonResponse;
import com.mycompany.carmanagement.web.json.response.EmployeeListJsonResponse;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "employee";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeListJsonResponse getAll(@RequestParam int jtStartIndex,
			@RequestParam int jtPageSize) {
		EmployeeListJsonResponse jstr;
		List<EmployeeJsonBean> employeeList;
		try {
			long employeeCount = employeeService.getCount();
			employeeList = employeeService.getAll(jtStartIndex/jtPageSize, jtPageSize);;
			jstr = new EmployeeListJsonResponse("OK", employeeList,
					employeeCount);
		} catch (Exception e) {
			jstr = new EmployeeListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeJsonResponse add(
			@ModelAttribute EmployeeJsonBean jsnEmployeeBean,
			BindingResult result) {
		EmployeeJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new EmployeeJsonResponse("ERROR",
					"Form invalid");
		}
		try {
			employeeService.add(jsnEmployeeBean);
			jsonJtableResponse = new EmployeeJsonResponse("OK", jsnEmployeeBean);
		} catch (Exception e) {
			jsonJtableResponse = new EmployeeJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeJsonResponse update(
			@ModelAttribute EmployeeJsonBean employeeBean, BindingResult result) {
		EmployeeJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new EmployeeJsonResponse("ERROR",
					"Form invalid");
		}
		try {
			employeeService.update(employeeBean);
			jsonJtableResponse = new EmployeeJsonResponse("OK", employeeBean);
		} catch (Exception e) {
			jsonJtableResponse = new EmployeeJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public EmployeeJsonResponse remove(@RequestParam String id) {
		EmployeeJsonResponse jsonJtableResponse;
		try {
			employeeService.delete(new Long(id));
			jsonJtableResponse = new EmployeeJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new EmployeeJsonResponse("ERROR",
					e.getMessage());
		}
		return jsonJtableResponse;
	}
}
