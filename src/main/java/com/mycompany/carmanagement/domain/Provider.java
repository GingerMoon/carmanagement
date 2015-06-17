package com.mycompany.carmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id = -1;
    private String name = "";
    private String description = "";

    protected Provider() { }
    protected Provider(long id) { this.id = id;}

    public Provider(String firstName, String lastName) {
        this.name = firstName;
        this.description = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Provider[id=%d, firstName='%s', lastName='%s']",
                id, name, description);
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

    /***********Getters & Setters********************/
    
}

