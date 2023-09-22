package com.sim.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
@Setter
@Getter

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @NotBlank(message = "Please Enter proper Customer Name")
    @Size(min = 2, message = "Name should be  attlist 5 character")
    @Size(max = 10, message = "Name should not be more than 10 character")
    private String customerFirstName;
    @Size(min = 2, message = "Name should be  attlist 5 character")
    @Size(max = 10, message = "Name should not be more than 10 character")
    private String customerLastName;
    @Size(min = 12, message = "aadhaarNumber should be  attlist 12 digit")
    @Size(max = 12, message = "aadhaarNumber should not be more than 12 digit")
    private String aadhaarNumber;

    private Long contactNumber;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String emailId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide a date.")
    private LocalDate dateOfBirth;
//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name ="sim_Id",referencedColumnName = "sim_Id")
//    private Sim sim;


}
