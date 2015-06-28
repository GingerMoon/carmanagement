package com.mycompany.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.carmanagement.domain.SalesRecord;
import com.mycompany.carmanagement.service.SalesRecordService;
import com.mycompany.carmanagement.web.json.bean.SalesRecordJsonBean;
import com.mycompany.carmanagement.web.json.response.SalesRecordJsonResponse;
import com.mycompany.carmanagement.web.json.response.SalesRecordListJsonResponse;

@Controller
@RequestMapping("/salesRecord")
public class SalesRecordController {

	@Autowired
	private SalesRecordService salesRecordService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "salesRecord";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public SalesRecordListJsonResponse getAll(@RequestParam int jtStartIndex, @RequestParam int jtPageSize,
			@ModelAttribute("salesRecord") SalesRecord salesRecord, BindingResult result) {
		SalesRecordListJsonResponse jstr;
		List<SalesRecordJsonBean> salesRecordList;
		try {
			long[] salesRecordCountTotalPrice = {0, -1}; 
			salesRecordList = salesRecordService.getAll(salesRecord, new PageRequest(jtStartIndex / jtPageSize, jtPageSize), salesRecordCountTotalPrice);
			SalesRecordJsonBean totalPriceElement = new SalesRecordJsonBean();
			totalPriceElement.setId("");
			totalPriceElement.setPrice(String.valueOf(salesRecordCountTotalPrice[1]));
			salesRecordList.add(totalPriceElement);
			jstr = new SalesRecordListJsonResponse("OK", salesRecordList, salesRecordCountTotalPrice[0]);
		} catch (Exception e) {
			jstr = new SalesRecordListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public SalesRecordJsonResponse add(@ModelAttribute SalesRecordJsonBean jsnSalesRecordBean, BindingResult result) {
		SalesRecordJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new SalesRecordJsonResponse("ERROR", "Form invalid");
		}
		try {
			salesRecordService.add(jsnSalesRecordBean);
			jsonJtableResponse = new SalesRecordJsonResponse("OK", jsnSalesRecordBean);
		} catch (Exception e) {
			jsonJtableResponse = new SalesRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public SalesRecordJsonResponse update(@ModelAttribute SalesRecordJsonBean salesRecordBean, BindingResult result) {
		SalesRecordJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new SalesRecordJsonResponse("ERROR", "Form invalid");
		}
		try {
			salesRecordService.update(salesRecordBean);
			jsonJtableResponse = new SalesRecordJsonResponse("OK", salesRecordBean);
		} catch (Exception e) {
			jsonJtableResponse = new SalesRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public SalesRecordJsonResponse remove(@RequestParam String id) {
		SalesRecordJsonResponse jsonJtableResponse;
		try {
			salesRecordService.delete(new Long(id));
			jsonJtableResponse = new SalesRecordJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new SalesRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}
}
