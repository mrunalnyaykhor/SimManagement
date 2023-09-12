package com.sim.management.dto;

import com.sim.management.entity.Customer;
import com.sim.management.entity.SimName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SimDto {
    private Long simId;
    @Enumerated(EnumType.STRING)
    private SimName simName;
    private Long simNumber;



}
