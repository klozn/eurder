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
        setFirstname(builder.firstname);
        setLastname(builder.lastname);
        assertValidEmail(builder.email);
        email = builder.email;
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNr);
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
        assertValidFirstName(firstname);
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        assertValidLastname(lastname);
        this.lastname = lastname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        assertValidPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void assertValidFirstName(String firstname) {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("Firstname is required.");
        }
    }

    private void assertValidLastname(String lastname) {
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname is required.");
        }
    }

    private void assertValidEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email provided.");
        }
    }

    private void assertValidPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !PhoneNumberValidator.getInstance().isValid(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number provided.");
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id='").append(id).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address=").append(address);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
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
