package com.switchfully.eurder.api.dto.customer;

public class CustomerDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String street;
    private String streetNr;
    private String city;
    private String postalCode;
    private String phoneNr;

    public CustomerDto setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CustomerDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public CustomerDto setStreetNr(String streetNr) {
        this.streetNr = streetNr;
        return this;
    }

    public CustomerDto setCity(String city) {
        this.city = city;
        return this;
    }

    public CustomerDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public CustomerDto setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
        return this;
    }
}
