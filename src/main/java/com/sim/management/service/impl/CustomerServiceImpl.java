package com.sim.management.service.impl;

import com.sim.management.dto.CustomerDto;
import com.sim.management.entity.Customer;
import com.sim.management.exception.CustomerException;
import com.sim.management.repository.CustomerRepository;
import com.sim.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmailId;

    @Override
    public CustomerDto saveCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setCustomerFirstName(dto.getCustomerFirstName());
        customer.setCustomerLastName(dto.getCustomerLastName());
        customer.setAadhaarNumber(dto.getAadhaarNumber());
        customer.setContactNumber(dto.getContactNumber());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setEmailId(dto.getEmailId());
        //  customer.set
        Customer saveCustomer = customerRepository.save(customer);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(saveCustomer.getCustomerId());
        customerDto.setCustomerFirstName(saveCustomer.getCustomerFirstName());
        customerDto.setCustomerLastName(saveCustomer.getCustomerLastName());
        customerDto.setAadhaarNumber(saveCustomer.getAadhaarNumber());
        customerDto.setContactNumber(saveCustomer.getContactNumber());
        customerDto.setDateOfBirth(saveCustomer.getDateOfBirth());
        customerDto.setEmailId(saveCustomer.getEmailId());

        return customerDto;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        if (customerRepository.findAll().isEmpty()) {
            throw new CustomerException("Customer Does not Exist");
        }
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        if (!customerList.isEmpty() && customerList != null) {
            for (Customer customer : customerList) {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setCustomerId(customer.getCustomerId());
                customerDto.setCustomerFirstName(customer.getCustomerFirstName());
                customerDto.setCustomerLastName(customer.getCustomerLastName());
                customerDto.setAadhaarNumber(customer.getAadhaarNumber());
                customerDto.setContactNumber(customer.getContactNumber());
                customerDto.setEmailId(customer.getEmailId());
                customerDto.setDateOfBirth(customer.getDateOfBirth());
                customerDtos.add(customerDto);
            }
        }
        return customerDtos;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        CustomerDto customerDto = new CustomerDto();
        if (Objects.nonNull(customer)) {
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setCustomerFirstName(customer.getCustomerFirstName());
            customerDto.setCustomerLastName(customer.getCustomerLastName());
            customerDto.setAadhaarNumber(customer.getAadhaarNumber());
            customerDto.setContactNumber(customer.getContactNumber());
            customerDto.setEmailId(customer.getEmailId());
            customerDto.setDateOfBirth(customer.getDateOfBirth());
        }

        return customerDto;
    }

    @Override
    public String deleteCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer customer1 = customer.get();
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
        }

        return "Customer Deleted Successfully____!!";
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long customerId) {


        Customer customer = customerRepository.findById(customerId).orElse(null);
        CustomerDto dto = null;
        if (Objects.nonNull(customer)) {
            customer.setCustomerId(customerId);
            customer.setCustomerFirstName(customerDto.getCustomerFirstName());
            customer.setCustomerLastName(customerDto.getCustomerLastName());
            customer.setAadhaarNumber(customerDto.getAadhaarNumber());
            customer.setContactNumber(customerDto.getContactNumber());
            customer.setEmailId(customerDto.getEmailId());
            customer.setDateOfBirth(customerDto.getDateOfBirth());

            Customer save = customerRepository.save(customer);

            dto = new CustomerDto();
            dto.setCustomerId(save.getCustomerId());
            dto.setEmailId(save.getEmailId());
            dto.setCustomerFirstName(save.getCustomerFirstName());
            dto.setCustomerLastName(save.getCustomerLastName());
            dto.setDateOfBirth(save.getDateOfBirth());
            dto.setAadhaarNumber(save.getAadhaarNumber());
            dto.setContactNumber(save.getContactNumber());

        }
        return dto;
    }

    @Override
    public List<CustomerDto> getCustomerDateOfBirth() {
        List<CustomerDto> birthdayList = new ArrayList<>();

        LocalDate date = LocalDate.now();
        List<Customer> list = customerRepository.findAll();
        for (Customer customer : list) {
            if (customer.getDateOfBirth().getMonth() == date.getMonth() && customer.getDateOfBirth().getDayOfMonth() == date.getDayOfMonth()) {
                CustomerDto customerDto = new CustomerDto();
                customerDto.setCustomerId(customer.getCustomerId());
                customerDto.setCustomerFirstName(customer.getCustomerFirstName());
                customerDto.setCustomerLastName(customer.getCustomerLastName());
                customerDto.setAadhaarNumber(customer.getAadhaarNumber());
                customerDto.setContactNumber(customer.getContactNumber());
                customerDto.setEmailId(customer.getEmailId());
                customerDto.setDateOfBirth(customer.getDateOfBirth());
                birthdayList.add(customerDto);
            }
        }
        return birthdayList;
    }

    @Override
    public String sendEmail(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            try {
                SimpleMailMessage simpleMaleMessage = new SimpleMailMessage();
                simpleMaleMessage.setFrom(fromEmailId);
                simpleMaleMessage.setTo(optionalCustomer.get().getEmailId());
                simpleMaleMessage.setText("Hello. \n Mr/Miss.  " +
                        optionalCustomer.get().getCustomerFirstName() + "  " +
                        optionalCustomer.get().getCustomerLastName() + "   \n"
                        + "Your Aadhaar no : " + optionalCustomer.get().getAadhaarNumber() + " \n" +
                        "" + "Your Date of Birth :" + optionalCustomer.get().getDateOfBirth() + "\n" +
                        "" + "Your Mobile Number :" + optionalCustomer.get().getContactNumber() + "\n" +
                        "" + "Alert! Your Jio Unlimited pack is\n" +
                        "Expiring soon. For Best Offers Click :jio.app.link/121\n" +
                        "1) 239: 1.5GB/D+ UL,28Days,\n" +
                        "2) 199: 1GB/D+UL,27Days");
                simpleMaleMessage.setSubject("Regards : \n Sim-Management team");
                javaMailSender.send(simpleMaleMessage);
                return "email send successfully to customer...!!";
            } catch (Exception exception) {
                exception.printStackTrace();
                return "Error while sending mail";
            }
        }
        return "Customer details not available";
    }

    @Override
    public String getBirthDayNotification() {
        List<Customer> customer = customerRepository.findAll();
        List<CustomerDto> birthdayNotificationList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        for (Customer customer1 : customer) {

            if (customer1.getDateOfBirth().getMonth() == date.getMonth()) {
                int i = date.getDayOfMonth();
                i += 7;
                if (customer1.getDateOfBirth().getDayOfMonth() == i) {
                    CustomerDto customerDto = new CustomerDto();
                    customerDto.setCustomerId(customer1.getCustomerId());
                    customerDto.setCustomerFirstName(customer1.getCustomerFirstName());
                    customerDto.setCustomerLastName(customer1.getCustomerLastName());
                    customerDto.setAadhaarNumber(customer1.getAadhaarNumber());
                    customerDto.setContactNumber(customer1.getContactNumber());
                    customerDto.setEmailId(customer1.getEmailId());
                    customerDto.setDateOfBirth(customer1.getDateOfBirth());
                    birthdayNotificationList.add(customerDto);
                }
            }
        }

        for (CustomerDto customerDto : birthdayNotificationList) {
            Long customerId = customerDto.getCustomerId();
            // sendEmail(customerId);
            try {
                SimpleMailMessage simpleMaleMessage = new SimpleMailMessage();
                simpleMaleMessage.setFrom(fromEmailId);
                simpleMaleMessage.setTo(customerDto.getEmailId());
                simpleMaleMessage.setText("Hello...Dear Customer \n Mr/Miss.  " + customerDto.getCustomerFirstName() + "  " +
                        customerDto.getCustomerLastName() + "  \n" +
                        "" + "your Mobile Number :" + customerDto.getContactNumber() + " \n"
                        + "Your Date of Birth :" + customerDto.getDateOfBirth() + " \n" +

                        "May the joy that you have spread in the past come back to you on this day.\n" +
                        " Wishing you a very happy birthday !”" +
                        customerDto.getCustomerFirstName() + " " +
                        customerDto.getCustomerLastName());
                simpleMaleMessage.setSubject("Regards : \n BirthDay-Wish");
                javaMailSender.send(simpleMaleMessage);
            } catch (Exception exception) {
                exception.printStackTrace();
                return "Error while sending mail";
            }
        }

        return "email send successfully to customer...!!";
    }


}
