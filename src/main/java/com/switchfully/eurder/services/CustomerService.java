package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.customer.*;
import com.switchfully.eurder.domain.exceptions.EmailAlreadyExistsException;
import com.switchfully.eurder.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.switchfully.eurder.api.dto.customer.CustomerDtoMapper.*;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void createNewCustomer(CreateCustomerDto customerDto) {
        assertEmailNotRegistered(customerDto.getEmail());
        customerRepo.save(toEntity(customerDto));
    }

    private void assertEmailNotRegistered(String email) {
        if (customerRepo.getAll().stream()
                .anyMatch(customer -> customer.getEmail().equals(email))) {
            throw new EmailAlreadyExistsException("The email address (" + email + ") is already registered to a customer.");
        }
    }

}
