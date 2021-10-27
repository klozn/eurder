package com.switchfully.eurder.domain.users;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final String id;
    private String firstname;
    private String lastname;
    private final String email;
    private Address address;
    private String phoneNumber;

    private Customer(Builder builder) {
        id = UUID.randomUUID().toString();
        firstname = builder.firstname;
        lastname = builder.lastname;
        email = builder.email;
        address = builder.address;
        phoneNumber = builder.phoneNr;
        assertValidCustomer();
    }

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

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void assertValidCustomer() {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname is required");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
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

    public static class Builder {
        private String firstname;
        private String lastname;
        private String email;
        private Address address;
        private String phoneNr;

        public Builder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withPhoneNr(String phoneNr) {
            this.phoneNr = phoneNr;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
