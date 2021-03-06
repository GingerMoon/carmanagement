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

import com.mycompany.carmanagement.domain.PurchaseRecord;
import com.mycompany.carmanagement.service.PurchaseRecordService;
import com.mycompany.carmanagement.web.json.bean.PurchaseRecordJsonBean;
import com.mycompany.carmanagement.web.json.response.PurchaseRecordJsonResponse;
import com.mycompany.carmanagement.web.json.response.PurchaseRecordListJsonResponse;

@Controller
@RequestMapping("/purchaseRecord")
public class PurchaseRecordController {

	@Autowired
	private PurchaseRecordService purchaseRecordService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "purchaseRecord";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public PurchaseRecordListJsonResponse getAll(@RequestParam int jtStartIndex, @RequestParam int jtPageSize,
			@ModelAttribute("purchaseRecord") PurchaseRecord purchaseRecord, BindingResult result) {
		PurchaseRecordListJsonResponse jstr;
		List<PurchaseRecordJsonBean> purchaseRecordList;
		try {
			long[] purchaseRecordCountTotalPrice = {0, -1}; 
			purchaseRecordList = purchaseRecordService.getAll(purchaseRecord, new PageRequest(jtStartIndex / jtPageSize, jtPageSize), purchaseRecordCountTotalPrice);
			PurchaseRecordJsonBean totalPriceElement = new PurchaseRecordJsonBean();
			totalPriceElement.setId("");
			totalPriceElement.setPrice(String.valueOf(purchaseRecordCountTotalPrice[1]));
			purchaseRecordList.add(totalPriceElement);
			jstr = new PurchaseRecordListJsonResponse("OK", purchaseRecordList, purchaseRecordCountTotalPrice[0]);
		} catch (Exception e) {
			jstr = new PurchaseRecordListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public PurchaseRecordJsonResponse add(@ModelAttribute PurchaseRecordJsonBean jsnPurchaseRecordBean, BindingResult result) {
		PurchaseRecordJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new PurchaseRecordJsonResponse("ERROR", "Form invalid");
		}
		try {
			purchaseRecordService.add(jsnPurchaseRecordBean);
			jsonJtableResponse = new PurchaseRecordJsonResponse("OK", jsnPurchaseRecordBean);
		} catch (Exception e) {
			jsonJtableResponse = new PurchaseRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public PurchaseRecordJsonResponse update(@ModelAttribute PurchaseRecordJsonBean purchaseRecordBean, BindingResult result) {
		PurchaseRecordJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new PurchaseRecordJsonResponse("ERROR", "Form invalid");
		}
		try {
			purchaseRecordService.update(purchaseRecordBean);
			jsonJtableResponse = new PurchaseRecordJsonResponse("OK", purchaseRecordBean);
		} catch (Exception e) {
			jsonJtableResponse = new PurchaseRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public PurchaseRecordJsonResponse remove(@RequestParam String id) {
		PurchaseRecordJsonResponse jsonJtableResponse;
		try {
			purchaseRecordService.delete(new Long(id));
			jsonJtableResponse = new PurchaseRecordJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new PurchaseRecordJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}
}
