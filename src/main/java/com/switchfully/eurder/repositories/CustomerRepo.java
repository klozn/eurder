package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.users.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class CustomerRepo {
    private final Set<Customer> customers = new HashSet<>();

    public Collection<Customer> getAll() {
        return Collections.unmodifiableSet(customers);
    }

    public boolean save(Customer customer) {
        assertEmailNotRegistered(customer.getEmail());
        return customers.add(customer);
    }

    private void assertEmailNotRegistered(String email) {
        if (getAll().stream()
                .anyMatch(customer -> customer.getEmail().equals(email))) {
            throw new IllegalStateException("The email address (" + email + ") is already registered to a customer.");
        }
    }

    public Customer getById(String customerId) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findAny()
                .orElse(null);
    }
}
