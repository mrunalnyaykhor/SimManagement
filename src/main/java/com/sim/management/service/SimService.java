package com.sim.management.service;

import com.sim.management.dto.SimDto;
import com.sim.management.entity.Sim;

import java.util.List;
import java.util.Optional;

public interface SimService {
    SimDto saveSim(SimDto simDto,Long customerId);

    List<SimDto> getAllSim();

    List<SimDto> getSimById(Long simId);

    SimDto updateSimById(SimDto simDto, Long simId);
}
