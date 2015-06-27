/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.CustomerJsonBean;

public class CustomerJsonResponse {

	private String Result;

	private CustomerJsonBean Records;

	private String Message;

	public CustomerJsonResponse() {
	}

	public CustomerJsonResponse(String Result) {
		this.Result = Result;
	}

	public CustomerJsonResponse(String Result, CustomerJsonBean Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public CustomerJsonResponse(String Result, String Message) {
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
	public CustomerJsonBean getRecords() {
		return Records;
	}

	public void setRecords(CustomerJsonBean Records) {
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
