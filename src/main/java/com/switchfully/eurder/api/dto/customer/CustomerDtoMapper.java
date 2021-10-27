package com.switchfully.eurder.api.dto.customer;

import com.switchfully.eurder.domain.users.Address;
import com.switchfully.eurder.domain.users.Customer;

public class CustomerDtoMapper {
    public static CustomerDto toDto(Customer customer){
        return new CustomerDto()
                .setId(customer.getId())
                .setFirstname(customer.getFirstname())
                .setLastname(customer.getLastname())
                .setEmail(customer.getEmail())
                .setStreet(customer.getAddress().getStreet())
                .setHouseNumber(customer.getAddress().getHouseNumber())
                .setCity(customer.getAddress().getCity())
                .setPostalCode(customer.getAddress().getPostalCode())
                .setCountry(customer.getAddress().getCountry())
                .setPhoneNumber(customer.getPhoneNumber());
    }

    public static Customer toEntity(CreateCustomerDto customerDto) {
        Address address = new Address.Builder()
                .withStreet(customerDto.getStreet())
                .withHouseNumber(customerDto.getHouseNumber())
                .withCity(customerDto.getCity())
                .withPostalCode(customerDto.getPostalCode())
                .withCountry(customerDto.getCountry())
                .build();

        return new Customer.Builder()
                .withFirstname(customerDto.getFirstname())
                .withLastname(customerDto.getLastname())
                .withEmail(customerDto.getEmail())
                .withAddress(address)
                .withPhoneNr(customerDto.getPhoneNumber())
                .build();
    }
}
