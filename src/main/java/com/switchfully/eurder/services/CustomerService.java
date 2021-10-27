package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.customer.CreateCustomerDto;
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

    public boolean createNewCustomer(CreateCustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("You can't create a Customer from a null object.");
        }
        return customerRepo.save(toEntity(customerDto));
    }

}
