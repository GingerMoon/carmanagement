/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.CarJsonBean;

import java.util.List;

public class CarListJsonResponse {

	private String Result;

	private List<CarJsonBean> Records;

	private long TotalRecordCount;

	private String Message;

	public CarListJsonResponse(String Result, List<CarJsonBean> Records, long TotalRecordCount) {
		this.Result = Result;
		this.Records = Records;
		this.TotalRecordCount = TotalRecordCount;
	}

	public CarListJsonResponse(String Result, String Message) {
		this.Result = Result;
		this.Message = Message;
	}

	@JsonProperty("Result")
	public String getResult() {
		return Result;
	}

	public void setResult(String Result) {
		this.Result = Result;
	}

	@JsonProperty("Records")
	public List<CarJsonBean> getRecords() {
		return Records;
	}

	public void setRecords(List<CarJsonBean> Records) {
		this.Records = Records;
	}

	@JsonProperty("TotalRecordCount")
	public long getTotalRecordCount() {
		return TotalRecordCount;
	}

	public void setTotalRecordCount(int TotalRecordCount) {
		this.TotalRecordCount = TotalRecordCount;
	}

	@JsonProperty("Message")
	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}
}
