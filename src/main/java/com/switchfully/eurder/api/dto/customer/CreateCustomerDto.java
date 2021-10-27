package com.switchfully.eurder.api.dto.customer;

public class CreateCustomerDto {
    private String firstname;
    private String lastname;
    private String email;
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateCustomerDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CreateCustomerDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CreateCustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateCustomerDto setStreet(String street) {
        this.street = street;
        return this;
    }

    public CreateCustomerDto setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public CreateCustomerDto setCity(String city) {
        this.city = city;
        return this;
    }

    public CreateCustomerDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public CreateCustomerDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public CreateCustomerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
