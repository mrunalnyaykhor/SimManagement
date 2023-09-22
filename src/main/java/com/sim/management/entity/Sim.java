package com.sim.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long simId;
    @Enumerated(EnumType.STRING)
//    @NotBlank(message = "Please Enter proper Sim Name")
//    @Size(min=2,message = "Name should be  attlist 2 character")
//    @Size(max=10,message = "Name should not be more than 10 character")
    private SimName simName;

  //  @Size(min=10,message = "simNumber should be  attlist 10 digit")
  //  @Size(max=10,message = "simNumber should not be more than 10 digit")
    private Long simNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="customerId",referencedColumnName = "customerId")
    private Customer customer;


}
