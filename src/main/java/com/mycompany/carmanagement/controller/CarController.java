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
import com.mycompany.carmanagement.service.CarService;
import com.mycompany.carmanagement.web.json.bean.CarJsonBean;
import com.mycompany.carmanagement.web.json.response.CarJsonResponse;
import com.mycompany.carmanagement.web.json.response.CarListJsonResponse;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "car";
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	@ResponseBody
	public CarListJsonResponse getAll(@RequestParam int jtStartIndex,
			@RequestParam int jtPageSize) {
		CarListJsonResponse jstr;
		List<CarJsonBean> carList;
		try {
			long carCount = carService.getCount();
			carList = carService.getAll(jtStartIndex/jtPageSize, jtPageSize);
			jstr = new CarListJsonResponse("OK", carList, carCount);
		} catch (Exception e) {
			jstr = new CarListJsonResponse("ERROR", e.getMessage());
		}

		return jstr;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public CarJsonResponse add(@ModelAttribute CarJsonBean jsnCarBean,
			BindingResult result) {
		CarJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new CarJsonResponse("ERROR", "Form invalid");
		}
		try {
			carService.add(jsnCarBean);
			jsonJtableResponse = new CarJsonResponse("OK", jsnCarBean);
		} catch (Exception e) {
			jsonJtableResponse = new CarJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public CarJsonResponse update(@ModelAttribute CarJsonBean carBean, BindingResult result) {
		CarJsonResponse jsonJtableResponse;
		if (result.hasErrors()) {
			jsonJtableResponse = new CarJsonResponse("ERROR", "Form invalid");
		}
		try {
			carService.update(carBean);
			jsonJtableResponse = new CarJsonResponse("OK", carBean);
		} catch (Exception e) {
			jsonJtableResponse = new CarJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public CarJsonResponse remove(@RequestParam String id) {
		CarJsonResponse jsonJtableResponse;
		try {
			carService.delete(new Long(id));
			jsonJtableResponse = new CarJsonResponse("OK");
		} catch (Exception e) {
			jsonJtableResponse = new CarJsonResponse("ERROR", e.getMessage());
		}
		return jsonJtableResponse;
	}
}
