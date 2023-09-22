package com.sim.management.service.impl;
import com.sim.management.dto.CustomerDto;
import com.sim.management.entity.Customer;
import com.sim.management.exception.CustomerException;
import com.sim.management.repository.CustomerRepository;
import com.sim.management.service.CustomerService;
import org.springframework.beans.BeanUtils;
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
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    Customer customer = new Customer();
    CustomerDto customerdto = new CustomerDto();
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmailId;

    @Override
    public CustomerDto saveCustomer(CustomerDto CustomerDto) {
        BeanUtils.copyProperties(CustomerDto, customer);
        customerRepository.save(customer);
        return CustomerDto;
    }
    @Override
    public List<CustomerDto> getAllCustomer() {
        if(customerRepository.findAll().isEmpty())
            throw new CustomerException("customers Data not present in Database");
        return customerRepository.findAll().stream().filter(Objects::nonNull).map(customer -> {
            BeanUtils.copyProperties(customer, customerdto);
            return customerdto;
        }).collect(Collectors.toList());
    }
    @Override
    public List<CustomerDto> getCustomerById(Long customerId) {
        if(customerRepository.findById(customerId).isEmpty())
           throw new CustomerException("customer not present");
        return customerRepository.findById(customerId).stream().filter(Objects::nonNull)
                .map(customer -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            return customerDto;
        }).collect(Collectors.toList());
    }
    @Override
    public String deleteCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerException("Customer does not exist ");
        }
        return "Customer Deleted Successfully____!!";
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long customerId) {
        Optional<Customer> customerOp = customerRepository.findById(customerId);
        if(customerRepository.findById(customerId).isEmpty())
            throw new CustomerException("customer not present");
        if (customerOp.isPresent()) {
            Customer customer = customerOp.get();
            BeanUtils.copyProperties(customerDto, customer);
            customerRepository.save(customer);
        }
        return customerDto;
    }

    @Override
    public List<CustomerDto> getCustomerDateOfBirth() {
        LocalDate date = LocalDate.now();
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getDateOfBirth().getMonth() == date.getMonth()
                        && customer.getDateOfBirth().getDayOfMonth() == date.getDayOfMonth())
                .map(customer1 -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer1, customerDto);
            return customerDto;
        }).collect(Collectors.toList());

    }
    @Override
    public String sendEmail(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            try {
                SimpleMailMessage simpleMaleMessage = new SimpleMailMessage();
                simpleMaleMessage.setFrom(fromEmailId);
                simpleMaleMessage.setTo(optionalCustomer.get().getEmailId());
                simpleMaleMessage.setText("Hello. \n Mr/Miss.  " + optionalCustomer.get().getCustomerFirstName() + "  " +
                        optionalCustomer.get().getCustomerLastName() + "   \n" + "Your Aadhaar no : " +
                        optionalCustomer.get().getAadhaarNumber() + " \n" + "" + "Your Date of Birth :" +
                        optionalCustomer.get().getDateOfBirth() + "\n" + "" + "Your Mobile Number :" +
                        optionalCustomer.get().getContactNumber() + "\n" + "" + "Alert! Your Jio Unlimited pack is\n" +
                        "Expiring soon. For Best Offers Click :jio.app.link/121\n" +
                        "1) 239: 1.5GB/D+ UL,28Days,\n" + "2) 199: 1GB/D+UL,27Days");
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
        List<Customer> list = customerRepository.findAll();
        LocalDate date = LocalDate.now();
        list.stream().filter(customer1 -> customer1.getDateOfBirth().getMonth() == date.getMonth()
                && customer1.getDateOfBirth().getDayOfMonth() == date.getDayOfMonth() + 7)
                .map(customer2 -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer2, customerDto);

            SimpleMailMessage simpleMaleMessage = new SimpleMailMessage();
            simpleMaleMessage.setFrom(fromEmailId);
            simpleMaleMessage.setTo(customer2.getEmailId());
            simpleMaleMessage.setText("Hello...Dear Customer \n Mr/Miss.  " + customerDto.getCustomerFirstName() + "  " + customerDto.getCustomerLastName() + "  \n" + "" + "your Mobile Number :" + customerDto.getContactNumber() + " \n" + "Your Date of Birth :" + customerDto.getDateOfBirth() + " \n" + "May the joy that you have spread in the past come back to you on this day.\n" + " Wishing you a very happy birthday !”" + customerDto.getCustomerFirstName() + " " + customerDto.getCustomerLastName());
            simpleMaleMessage.setSubject("Regards : \n BirthDay-Wish");
            javaMailSender.send(simpleMaleMessage);
            return customerDto;
        }).collect(Collectors.toList());

        return "email send successfully to customer...!!";
    }
}

