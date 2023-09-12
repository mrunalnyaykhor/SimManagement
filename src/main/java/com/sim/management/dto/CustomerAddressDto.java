package com.sim.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerAddressDto {
    private Long addressId;
    private String address;
    private String district;
    private String state;
    private String country;
    private int zipCode;
    private Long customerId;
}
