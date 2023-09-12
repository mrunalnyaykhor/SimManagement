package com.sim.management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class CustomerDto {

    private Long customerId;

    private String customerFirstName;
    private String customerLastName;
    private String aadhaarNumber;
    private Long contactNumber;
    private String emailId;
    private LocalDate dateOfBirth;


}
