package com.switchfully.eurder.api.dto.customer;

public class CustomerDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private AddressDto address = new AddressDto();
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

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
        this.address.setStreet(street);
        return this;
    }

    public CustomerDto setHouseNumber(String houseNumber) {
        this.address.setHouseNumber(houseNumber);
        return this;
    }

    public CustomerDto setCity(String city) {
        this.address.setCity(city);
        return this;
    }

    public CustomerDto setPostalCode(String postalCode) {
        this.address.setPostalCode(postalCode);
        return this;
    }

    public CustomerDto setCountry(String country) {
        this.address.setCountry(country);
        return this;
    }

    public CustomerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
