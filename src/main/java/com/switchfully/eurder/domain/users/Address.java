package com.switchfully.eurder.domain.users;

import java.util.Objects;

public class Address {
    private final String street;
    private final String streetNr;
    private final String city;
    private final String postalCode;

    private Address(Builder builder) {
        street = builder.street;
        streetNr = builder.streetNr;
        city = builder.city;
        postalCode = builder.postalCode;
        assertNonNullOrBlankFields();
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

    private void assertNonNullOrBlankFields() {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (streetNr == null || streetNr.isBlank()) {
            throw new IllegalArgumentException("StreetNumber is required");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("PostalCode is required");
        }
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

    public static class Builder {
        private String street;
        private String streetNr;
        private String city;
        private String postalCode;

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withStreetNr(String streetNr) {
            this.streetNr = streetNr;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
