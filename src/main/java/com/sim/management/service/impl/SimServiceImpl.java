package com.sim.management.service.impl;

import com.sim.management.dto.SimDto;
import com.sim.management.entity.Sim;
import com.sim.management.repository.SimRepository;
import com.sim.management.service.SimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class SimServiceImpl implements SimService {
    @Autowired
    private SimRepository simRepository;

    @Override
    public SimDto saveSim(SimDto dto) {

        Sim sim = new Sim();
        // sim.setSimId(dto.getId());
        sim.setSimName(dto.getSimName());
        sim.setSimNumber(dto.getSimNumber());
        Sim save = simRepository.save(sim);
        SimDto simDto = new SimDto();
        //simDto.setId(save.getSimId());
        simDto.setSimNumber(save.getSimNumber());
        simDto.setSimName(save.getSimName());

        return simDto;
    }

    @Override
    public List<SimDto> getAllSim() {
        List<Sim> listOfSim = simRepository.findAll();
        List<SimDto> simDtos = new LinkedList<>();
        //  for()

        return simDtos;
    }

    @Override
    public SimDto getSimById(Long simId) {

        Sim sim = simRepository.findById(simId).get();
        SimDto simDto = new SimDto();
        simDto.setSimName(sim.getSimName());
        simDto.setSimNumber(sim.getSimNumber());
       // simDto.setId(sim.getSimId());
        return simDto;
    }

    @Override
    public SimDto updateSimById(SimDto simDto, Long simId) {
        Sim sim = simRepository.findById(simId).orElse(null);
        SimDto dto = null;
        if (Objects.nonNull(sim)) {
            sim.setSimId(simId);
            sim.setSimName(simDto.getSimName());
            sim.setSimNumber(simDto.getSimNumber());
            Sim save = simRepository.save(sim);

            dto = new SimDto();
            //dto.setId(save.getSimId());
            dto.setSimName(save.getSimName());
            dto.setSimNumber(save.getSimNumber());
        }
        return null;

    }
}
