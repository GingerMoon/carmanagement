package com.mycompany.carmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	private String name = "";
	private String description = "";

	public Car() {
	}

	public Car(long id) {
		this.id = id;
	}

	public Car(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, name='%s', name='%s']", id, name, description);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*********** Getters & Setters ********************/

}
