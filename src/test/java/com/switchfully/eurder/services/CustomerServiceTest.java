package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.customer.CreateCustomerDto;
import com.switchfully.eurder.repositories.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerRepo repo;
    private CustomerService service;

    @BeforeEach
    void init() {
        repo = new CustomerRepo();
        service = new CustomerService(repo);
    }

    @Test
    @DisplayName("creating a new customer saves customer to repo")
    void whenCreatingNewCustomer_thenAddToRepo() {
        CreateCustomerDto customerDto = new CreateCustomerDto()
                .setFirstname("testFirstname")
                .setLastname("testLastname")
                .setEmail("test@mail.com")
                .setStreet("testStreet")
                .setHouseNumber("testHouseNumber")
                .setCity("testCity")
                .setPostalCode("testPostal")
                .setCountry("testCountry");
        int repoSize = repo.getAll().size();

        assertTrue(service.createNewCustomer(customerDto));
        assertEquals(repoSize + 1, repo.getAll().size());
    }

}