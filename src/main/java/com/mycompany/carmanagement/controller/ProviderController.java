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

import com.mycompany.carmanagement.domain.Provider;
import com.mycompany.carmanagement.service.ProviderService;
import com.mycompany.carmanagement.service.ProviderService;
import com.mycompany.carmanagement.web.json.bean.ProviderJsonBean;
import com.mycompany.carmanagement.web.json.response.ProviderJsonResponse;
import com.mycompany.carmanagement.web.json.response.ProviderListJsonResponse;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "provider";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public ProviderListJsonResponse getAll(@RequestParam int jtStartIndex, @RequestParam int jtPageSize) {
		ProviderListJsonResponse jstr;
		List<ProviderJsonBean> providerList;
		try {
			long providerCount = providerService.getCount();
			providerList = providerService.getAll(jtStartIndex / jtPageSize, jtPageSize);
			jstr = new ProviderListJsonResponse("OK", providerList, providerCount);
		} catch (Exception e) {
			jstr = new ProviderListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProviderJsonResponse add(@ModelAttribute ProviderJsonBean jsnProviderBean, BindingResult result) {
		ProviderJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new ProviderJsonResponse("ERROR", "Form invalid");
		}
		try {
			providerService.add(jsnProviderBean);
			jsonJtableResponse = new ProviderJsonResponse("OK", jsnProviderBean);
		} catch (Exception e) {
			jsonJtableResponse = new ProviderJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProviderJsonResponse update(@ModelAttribute ProviderJsonBean providerBean, BindingResult result) {
		ProviderJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new ProviderJsonResponse("ERROR", "Form invalid");
		}
		try {
			providerService.update(providerBean);
			jsonJtableResponse = new ProviderJsonResponse("OK", providerBean);
		} catch (Exception e) {
			jsonJtableResponse = new ProviderJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ProviderJsonResponse remove(@RequestParam String id) {
		ProviderJsonResponse jsonJtableResponse;
		try {
			providerService.delete(new Long(id));
			jsonJtableResponse = new ProviderJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new ProviderJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}
}
