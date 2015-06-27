/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.EmployeeJsonBean;

public class EmployeeJsonResponse {

	private String Result;

	private EmployeeJsonBean Records;

	private String Message;

	public EmployeeJsonResponse() {
	}

	public EmployeeJsonResponse(String Result) {
		this.Result = Result;
	}

	public EmployeeJsonResponse(String Result, EmployeeJsonBean Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public EmployeeJsonResponse(String Result, String Message) {
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
	public EmployeeJsonBean getRecords() {
		return Records;
	}

	public void setRecords(EmployeeJsonBean Records) {
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
