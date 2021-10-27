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
                .setStreetNr(customer.getAddress().getStreetNr())
                .setCity(customer.getAddress().getCity())
                .setPostalCode(customer.getAddress().getPostalCode())
                .setPhoneNr(customer.getPhoneNr());
    }

    public static Customer toEntity(CreateCustomerDto customerDto) {
        Address address = new Address.Builder()
                .withStreet(customerDto.getStreet())
                .withStreetNr(customerDto.getStreetNr())
                .withCity(customerDto.getCity())
                .withPostalCode(customerDto.getPostalCode())
                .build();

        return new Customer.Builder()
                .withFirstname(customerDto.getFirstname())
                .withLastname(customerDto.getLastname())
                .withEmail(customerDto.getEmail())
                .withAddress(address)
                .withPhoneNr(customerDto.getPhoneNr())
                .build();
    }
}
