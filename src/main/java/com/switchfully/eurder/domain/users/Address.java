package com.switchfully.eurder.domain.users;

import java.util.Objects;

public class Address {
    private String street;
    private String streetNr;
    private String city;
    private String postalCode;

    public Address(String street, String streetNr, String city, String postalCode) {
        setStreet(street);
        setStreetNr(streetNr);
        setCity(city);
        setPostalCode(postalCode);
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

    public void setStreet(String street) {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street is required");
        }
        this.street = street;
    }

    public void setStreetNr(String streetNr) {
        if (streetNr == null || streetNr.isBlank()) {
            throw new IllegalArgumentException("StreetNumber is required");
        }
        this.streetNr = streetNr;
    }

    public void setCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required");
        }
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("PostalCode is required");
        }
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && streetNr.equals(address.streetNr) && postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNr, postalCode);
    }
}
