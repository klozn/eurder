package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.customer.CreateCustomerDto;
import com.switchfully.eurder.api.dto.customer.CustomerDto;
import com.switchfully.eurder.api.dto.customer.CustomerDtoMapper;
import com.switchfully.eurder.domain.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.domain.users.Customer;
import com.switchfully.eurder.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerDtoMapper mapper;
    private final AdminService adminService;

    @Autowired
    public CustomerService(CustomerRepo repo, CustomerDtoMapper mapper, AdminService adminService) {
        this.repo = repo;
        this.mapper = mapper;
        this.adminService = adminService;
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

    public List<CustomerDto> getAllCustomers(String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        return repo.getAll().stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(CustomerDto::getLastname).thenComparing(CustomerDto::getFirstname))
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(String customerId, String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        Customer customer = fetchCustomerIfExistsElseThrowException(customerId);
        return mapper.toDto(customer);
    }

    public Customer fetchCustomerIfExistsElseThrowException(String customerId) {
        Customer customer = repo.getById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("No customer found with id: " + customerId);
        }
        return customer;
    }
}
