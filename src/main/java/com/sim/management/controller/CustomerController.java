package com.sim.management.controller;

import com.sim.management.dto.CustomerDto;
import com.sim.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(customerService.saveCustomer(CustomerDto));

    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {

        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<List<CustomerDto>> getCustomerById(@PathVariable Long customerId) {

        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomerDto(@RequestBody CustomerDto customerDto, @PathVariable("customerId") Long customerId) {

        return ResponseEntity.ok(customerService.updateCustomer(customerDto, customerId));
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public String deleteCustomerById(@PathVariable Long customerId) {
        return customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/getBirthdayList")
    public ResponseEntity<List<CustomerDto>> getCustomerDateOfBirth() {
        return ResponseEntity.ok(customerService.getCustomerDateOfBirth());
    }

    @GetMapping("/getEmailById/{customerId}")
    public ResponseEntity<String> sendEmail(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.sendEmail(customerId));
    }

    @GetMapping("/getBirthdayNotification")
    public String birthDayNotify() {
        return customerService.getBirthDayNotification();

    }


}
