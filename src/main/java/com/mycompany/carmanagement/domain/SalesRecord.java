package com.mycompany.carmanagement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SalesRecord {

    private long id;
    private Car car;
    private Customer customer;
    private Employee employee;
    private long price;
    private Date beginDate;
    private Date endDate;
    private String description;

    public SalesRecord() {
    	this.car = new Car();
    	this.customer = new Customer();
    	this.employee = new Employee();
    	this.beginDate = new Date();
    	this.endDate = new Date();
    	this.description = new String();
    	
    }

    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "CAR_ID")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "PRICE")
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Column(name = "BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SalesRecord(long id, Car car, Customer customer, Employee employee,
			long price, Date beginDate, Date endDate, String description) {
		super();
		this.id = id;
		this.car = car;
		this.customer = customer;
		this.employee = employee;
		this.price = price;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
	}

	
    
}

