package com.switchfully.eurder.api.dto.customer;

import com.switchfully.eurder.domain.users.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoMapper {

    public AddressDto toDto(Address address) {
        return new AddressDto()
                .setStreet(address.getStreet())
                .setHouseNumber(address.getHouseNumber())
                .setPostalCode(address.getPostalCode())
                .setCity(address.getCity())
                .setCountry(address.getCountry());
    }
}
