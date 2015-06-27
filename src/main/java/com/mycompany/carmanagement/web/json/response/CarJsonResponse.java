/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.response;

//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.carmanagement.web.json.bean.CarJsonBean;

public class CarJsonResponse {

	private String Result;

	private CarJsonBean Records;

	private String Message;

	public CarJsonResponse() {
	}

	public CarJsonResponse(String Result) {
		this.Result = Result;
	}

	public CarJsonResponse(String Result, CarJsonBean Records) {
		this.Result = Result;
		this.Records = Records;
	}

	public CarJsonResponse(String Result, String Message) {
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
	public CarJsonBean getRecords() {
		return Records;
	}

	public void setRecords(CarJsonBean Records) {
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
