package com.app.entity;

import java.util.UUID;

public class Customer extends AggregateRoot<UUID>{
	private String username;
    private String firstName;
    private String lastName;

    public Customer(UUID customerId, String username, String firstName, String lastName) {
        super.setId(customerId);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(UUID customerId) {
        super.setId(customerId);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
