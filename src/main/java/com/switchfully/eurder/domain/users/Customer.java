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
    private String phoneNr;

    private Customer(Builder builder) {
        id = UUID.randomUUID().toString();
        setFirstname(builder.firstname);
        setLastname(builder.lastname);
        if (!EmailValidator.getInstance().isValid(builder.email)) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
        this.email = builder.email;
        setAddress(builder.address);
        setPhoneNr(builder.phoneNr);
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
