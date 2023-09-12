package com.sim.management.controller;

import com.sim.management.dto.CustomerDto;
import com.sim.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto CustomerDto) {
        CustomerDto customerDto = customerService.saveCustomer(CustomerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        List<CustomerDto> customerList = customerService.getAllCustomer();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<CustomerDto>(customer, HttpStatus.OK);
    }

    @PutMapping("/customer/{customerId}")
    public CustomerDto updateCustomerDto(@RequestBody CustomerDto customerDto, @PathVariable("customerId") Long customerId) {
        CustomerDto customerDto1 = customerService.updateCustomer(customerDto, customerId);
        return customerDto1;
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public String deleteCustomerById(@PathVariable Long customerId) {
        return customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/getBirthdayList")
    public ResponseEntity<List<CustomerDto>> getCustomerDateOfBirth() {
        List<CustomerDto> customerDtoList = customerService.getCustomerDateOfBirth();
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/getEmailById/{customerId}")
    public ResponseEntity<String> sendEmail(@PathVariable Long customerId) {
        return new ResponseEntity<String>(customerService.sendEmail(customerId), HttpStatus.OK);
    }

    @GetMapping("/getBirthdayNotification")
    public String birthDayNotify() {
        return customerService.getBirthDayNotification();

    }


}
