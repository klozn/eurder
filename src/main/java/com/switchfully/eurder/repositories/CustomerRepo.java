package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.exceptions.EmailAlreadyExistsException;
import com.switchfully.eurder.domain.users.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepo {
    private final Map<String, Customer> customers = new HashMap<>();

    public Collection<Customer> getAll() {
        return customers.values();
    }

    public Customer save(Customer customer) {
        assertEmailNotRegistered(customer.getEmail());
        return customers.put(customer.getId(), customer);
    }

    private void assertEmailNotRegistered(String email) {
        if (getAll().stream()
                .anyMatch(customer -> customer.getEmail().equals(email))) {
            throw new EmailAlreadyExistsException("The email address (" + email + ") is already registered to a customer.");
        }
    }
}
