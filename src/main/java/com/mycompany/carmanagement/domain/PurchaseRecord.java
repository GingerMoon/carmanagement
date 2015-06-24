package com.mycompany.carmanagement.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PurchaseRecord {

    private long id = -1;
    private Car car;
    private Provider provider;
    private Employee employee;
    private long price = -1;
    private Date beginDate;
    private Date endDate;
    private String description = "";

    public PurchaseRecord() {
    	this.car = new Car();
    	this.provider = new Provider();
    	this.employee = new Employee();
    	this.beginDate = new Date();
    	this.endDate = new Date();
    	this.description = new String();
    	
    }
	
	
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "car_id")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne
	@JoinColumn(name = "provider_id")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@ManyToOne
	@JoinColumn(name = "employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "price")
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Column(name = "begin_date")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PurchaseRecord(long id, Car car, Provider provider, Employee employee,
			long price, Date beginDate, Date endDate, String description) {
		super();
		this.id = id;
		this.car = car;
		this.provider = provider;
		this.employee = employee;
		this.price = price;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
	}

	
    
}

