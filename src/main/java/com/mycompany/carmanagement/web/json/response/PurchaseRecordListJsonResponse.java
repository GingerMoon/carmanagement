/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.PurchaseRecordJsonBean;

import java.util.List;

public class PurchaseRecordListJsonResponse {

	private String Result;

	private List<PurchaseRecordJsonBean> Records;

	private long TotalRecordCount;

	private String Message;

	public PurchaseRecordListJsonResponse(String Result, List<PurchaseRecordJsonBean> Records, long TotalRecordCount) {
		this.Result = Result;
		this.Records = Records;
		this.TotalRecordCount = TotalRecordCount;
	}

	public PurchaseRecordListJsonResponse(String Result, String Message) {
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
	public List<PurchaseRecordJsonBean> getRecords() {
		return Records;
	}

	public void setRecords(List<PurchaseRecordJsonBean> Records) {
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
