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
        customerRepo.save(toEntity(customerDto));
    }

}
