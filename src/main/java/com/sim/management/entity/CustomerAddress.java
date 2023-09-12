package com.sim.management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String address;
    private String district;
    private String state;
    private String country;
    private int zipCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="customerId",referencedColumnName = "customerId")
    private Customer customer;

}
