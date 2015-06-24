/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carmanagement.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesRecordJsonBean {
    private String id = "-1";
    private String carId;
    private String carName;
    private String employeeId;
    private String employeeName;
    private String customerId;
    private String customerName;
    private String beginDate;
    private String endDate;
    private String description;
    private String price;

    @JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("carId")
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@JsonProperty("carName")
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@JsonProperty("employeeId")
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@JsonProperty("employeeName")
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@JsonProperty("customerId")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("customerName")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("beginDate")
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty("price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
