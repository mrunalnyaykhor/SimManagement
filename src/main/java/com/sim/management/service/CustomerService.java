package com.sim.management.service;

import com.sim.management.dto.CustomerDto;
import com.sim.management.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomer();

    List<CustomerDto> getCustomerById(Long customerId);

    String deleteCustomerById(Long customerId);


    CustomerDto updateCustomer(CustomerDto customerDto,Long customerId);

    List<CustomerDto> getCustomerDateOfBirth();


    String sendEmail(Long customerId);

    String getBirthDayNotification();


}
