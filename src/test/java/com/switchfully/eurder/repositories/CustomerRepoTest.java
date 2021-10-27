package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.users.Address;
import com.switchfully.eurder.domain.users.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepoTest {

    private CustomerRepo repo;
    private static Customer testCustomer;

    @BeforeAll
    static void init() {
        testCustomer = new Customer.Builder()
                .withFirstname("TestFirstname")
                .withLastname("TestLastname")
                .withEmail("testEmail@test.com")
                .withPhoneNr("+32485945012")
                .withAddress(
                        new Address.Builder()
                                .withStreet("TestStreet")
                                .withHouseNumber("13 A")
                                .withCity("TestCity")
                                .withPostalCode("9999")
                                .withCountry("TestCountry")
                                .build())
                .build();
    }

    @BeforeEach
    void reset() {
        repo = new CustomerRepo();
    }

    @Test
    @DisplayName("You can save a customer to the repo")
    void whenSavingCustomer_thenCustomerIsAddedToTheSet() {
        int repoSize = repo.getAll().size();

        repo.save(testCustomer);

        assertEquals(repoSize + 1, repo.getAll().size());
    }

    @Test
    @DisplayName("If email is already registered throw exception")
    void save_whenCustomerEmailAlreadyExistsInRepo_throwIllegalStateException() {
        repo.save(testCustomer);

        Customer customerWithSameEmail = new Customer.Builder()
                .withFirstname("dummy")
                .withLastname("dummy")
                .withEmail("testEmail@test.com")
                .withAddress(
                        new Address.Builder()
                                .withStreet("dummy")
                                .withHouseNumber("dummy")
                                .withCity("dummy")
                                .withPostalCode("dummy")
                                .withCountry("dummy")
                                .build())
                .build();

        String exceptionMessage = assertThrows(IllegalStateException.class, () -> repo.save(customerWithSameEmail)).getMessage();
        assertEquals("The email address (testEmail@test.com) is already registered to a customer.", exceptionMessage);
    }
}