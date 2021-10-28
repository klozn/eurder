package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.customer.CreateCustomerDto;
import com.switchfully.eurder.api.dto.customer.CustomerDtoMapper;
import com.switchfully.eurder.domain.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerDtoMapper mapper;

    @Autowired
    public CustomerService(CustomerRepo repo, CustomerDtoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public boolean createNewCustomer(CreateCustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("You can't create a Customer from a null object.");
        }
        return repo.save(mapper.toEntity(customerDto));
    }

    public void assertCustomerId(String userId) {
        if (repo.getAll().stream().noneMatch(customer -> customer.getId().equals(userId))) {
            throw new UnauthorizedUserException(userId + " is not a customer id");
        }
    }

}
