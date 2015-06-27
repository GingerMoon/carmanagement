/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.SalesRecordJsonBean;

public class SalesRecordJsonResponse {

	private String Result;

	private SalesRecordJsonBean Records;

	private String Message;

	public SalesRecordJsonResponse() {
	}

	public SalesRecordJsonResponse(String Result) {
		this.Result = Result;
	}

	public SalesRecordJsonResponse(String Result, SalesRecordJsonBean Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public SalesRecordJsonResponse(String Result, String Message) {
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
	public SalesRecordJsonBean getRecords() {
		return Records;
	}

	public void setRecords(SalesRecordJsonBean Records) {
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
