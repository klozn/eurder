package com.switchfully.eurder.domain.users;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private static Address testAddress;

    @BeforeAll
    static void init() {
        testAddress = new Address.Builder()
                .withStreet("testStreet")
                .withHouseNumber("13 A")
                .withCity("testCity")
                .withPostalCode("6521")
                .withCountry("testCountry")
                .build();
    }

    @Test
    @DisplayName("Building customer without firstname throws exception")
    void whenBuildingCustomerWithFirstnameBlank_thenThrowIllegalArgumentException() {
        String exceptionMessage =
                assertThrows(IllegalArgumentException.class, () ->
                        new Customer.Builder()
                                .withFirstname("")
                                .withLastname("testLastname")
                                .withEmail("testEmail@test.com")
                                .withAddress(testAddress)
                                .build())
                        .getMessage();
        assertEquals("Firstname is required.", exceptionMessage);
    }

    @Test
    @DisplayName("Building customer without lastname throws exception")
    void whenBuildingCustomerWithLastnameBlank_thenThrowIllegalArgumentException() {
        String exceptionMessage =
                assertThrows(IllegalArgumentException.class, () ->
                        new Customer.Builder()
                                .withFirstname("testFirstname")
                                .withLastname("")
                                .withEmail("testEmail@test.com")
                                .withAddress(testAddress)
                                .build())
                        .getMessage();
        assertEquals("Lastname is required.", exceptionMessage);
    }

    @Test
    @DisplayName("Building customer without email throws exception")
    void whenBuildingCustomerWithEmailBlank_thenThrowIllegalArgumentException() {
        String exceptionMessage =
                assertThrows(IllegalArgumentException.class, () ->
                        new Customer.Builder()
                                .withFirstname("testFirstname")
                                .withLastname("testLastname")
                                .withEmail("")
                                .withAddress(testAddress)
                                .build())
                        .getMessage();
        assertEquals("Email is required.", exceptionMessage);
    }

    @Test
    @DisplayName("Building customer with invalid email throws exception")
    void whenBuildingCustomerWithInvalidEmail_thenThrowIllegalArgumentException() {
        String exceptionMessage =
                assertThrows(IllegalArgumentException.class, () ->
                        new Customer.Builder()
                                .withFirstname("testFirstname")
                                .withLastname("testLastname")
                                .withEmail("@mail.com")
                                .withAddress(testAddress)
                                .build())
                        .getMessage();
        assertEquals("Invalid email provided.", exceptionMessage);
    }

    @Test
    @DisplayName("Building customer with invalid phone number throws exception")
    void whenBuildingCustomerWithInvalidPhoneNumber_thenThrowIllegalArgumentException() {
        String exceptionMessage =
                assertThrows(IllegalArgumentException.class, () ->
                        new Customer.Builder()
                                .withFirstname("testFirstname")
                                .withLastname("testLastname")
                                .withEmail("test@mail.com")
                                .withAddress(testAddress)
                                .withPhoneNr("6841268")
                                .build())
                        .getMessage();
        assertEquals("Invalid phone number provided.", exceptionMessage);
    }
}