package com.switchfully.eurder.api.dto.customer;

public class CreateCustomerDto {
    private String firstname;
    private String lastname;
    private String email;
    private String street;
    private String streetNr;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNr;

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

    public String getStreetNr() {
        return streetNr;
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

    public String getPhoneNr() {
        return phoneNr;
    }
}
