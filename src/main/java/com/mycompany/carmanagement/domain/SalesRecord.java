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
public class SalesRecord {

    private long id = -1;
//	@ManyToOne
//	@JoinColumn(name = "car_id")
    private Car car;
//	@ManyToOne
//	@JoinColumn(name = "customer_id")
    private Customer customer;
//	@ManyToOne
//	@JoinColumn(name = "employee_id")
    private Employee employee;
    private long price = -1;
    private Date beginDate;
    private Date endDate;
    private String description = "";

    public SalesRecord() {
    	this.car = new Car();
    	this.customer = new Customer();
    	this.employee = new Employee();
    	this.beginDate = new Date();
    	this.endDate = new Date();
    	this.description = new String();
    	
    }


//    @Column(name = "car_id", insertable=false, updatable=false)
//	public long getCar_id() {
//		return car.getId();
//	}
//	public void setCar_id(long car_id) {
//		car.setId(car_id);
//	}
//
//	@Column(name = "customer_id", insertable=false, updatable=false)
//	public long getCustomer_id() {
//		return customer.getId();
//	}
//	public void setCustomer_id(long car_id) {
//		customer.setId(car_id);
//	}
//
//	@Column(name = "employee_id", insertable=false, updatable=false)
//	public long getEmployee_id() {
//		return employee.getId();
//	}
//	public void setEmployee_id(long car_id) {
//		employee.setId(car_id);
//	}
	
	
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
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

