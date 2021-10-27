package com.switchfully.eurder.domain.users;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
    private String phoneNr;

    public Customer(String firstname, String lastname, String email, Address address, String phoneNr) {
        id = UUID.randomUUID().toString();
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setAddress(address);
        setPhoneNr(phoneNr);
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname is required");
        }
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname is required");
        }
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) || Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
