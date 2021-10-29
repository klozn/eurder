package com.switchfully.eurder.domain.users;

import org.apache.commons.validator.routines.EmailValidator;

public class Admin {
    private final String id;
    private String firstname;
    private String lastname;
    private final String email;

    private Admin(Builder builder) {
        id = builder.id;
        setFirstname(builder.firstname);
        setLastname(builder.lastname);
        assertValidEmail(builder.email);
        email = builder.email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstname(String firstname) {
        assertValidFirstName(firstname);
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        assertValidLastname(lastname);
        this.lastname = lastname;
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

    public static class Builder {
        private String id;
        private String firstname;
        private String lastname;
        private String email;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

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

        public Admin build() {
            return new Admin(this);
        }
    }
}
