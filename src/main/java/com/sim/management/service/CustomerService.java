package com.sim.management.service;

import com.sim.management.dto.CustomerDto;
import com.sim.management.entity.Customer;
import org.springframework.http.HttpStatusCode;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customer);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(Long customerId);

    String deleteCustomerById(Long customerId);


    CustomerDto updateCustomer(CustomerDto customerDto,Long customerId);

    List<CustomerDto> getCustomerDateOfBirth();


    String sendEmail(Long customerId);

    String getBirthDayNotification();
}
