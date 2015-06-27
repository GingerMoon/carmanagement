/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.PurchaseRecordJsonBean;

public class PurchaseRecordJsonResponse {

	private String Result;

	private PurchaseRecordJsonBean Records;

	private String Message;

	public PurchaseRecordJsonResponse() {
	}

	public PurchaseRecordJsonResponse(String Result) {
		this.Result = Result;
	}

	public PurchaseRecordJsonResponse(String Result, PurchaseRecordJsonBean Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public PurchaseRecordJsonResponse(String Result, String Message) {
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

	@JsonProperty("Record")
	public PurchaseRecordJsonBean getRecords() {
		return Records;
	}

	public void setRecords(PurchaseRecordJsonBean Records) {
		this.Records = Records;
	}

	@JsonProperty("Message")
	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

}
