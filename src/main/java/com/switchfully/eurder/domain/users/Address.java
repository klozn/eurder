package com.switchfully.eurder.domain.users;

import java.util.Objects;

public class Address {
    private final String street;
    private final String houseNumber;
    private final String city;
    private final String postalCode;
    private final String country;

    private Address(Builder builder) {
        street = builder.street;
        houseNumber = builder.houseNumber;
        city = builder.city;
        postalCode = builder.postalCode;
        country = builder.country;
        assertValidAddress();
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

    private void assertValidAddress() {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street is required");
        }
        if (houseNumber == null || houseNumber.isBlank()) {
            throw new IllegalArgumentException("StreetNumber is required");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("PostalCode is required");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country is required");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && houseNumber.equals(address.houseNumber) && postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, postalCode);
    }



    public static class Builder {
        private String street;
        private String houseNumber;
        private String city;
        private String postalCode;
        private String country;

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
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

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
