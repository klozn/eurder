package com.switchfully.eurder.services;

import com.switchfully.eurder.domain.users.Address;
import com.switchfully.eurder.domain.users.Customer;
import com.switchfully.eurder.repositories.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

    @Nested
    @DisplayName("Creating a new customer")
    class CustomerCreation {

        @Test
        @DisplayName("If email already registered, throw exception")
        void whenEmailAlreadyExists_thenThrowException() {


        }
    }

}