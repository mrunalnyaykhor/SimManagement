package com.sim.management.service.impl;
import com.sim.management.dto.CustomerDto;
import com.sim.management.dto.SimDto;
import com.sim.management.entity.Customer;
import com.sim.management.entity.Sim;
import com.sim.management.exception.SimException;
import com.sim.management.repository.CustomerRepository;
import com.sim.management.repository.SimRepository;
import com.sim.management.service.SimService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimServiceImpl implements SimService {
    Sim sim = new Sim();
    Customer customer = new Customer();
    SimDto dto = new SimDto();
    CustomerDto customerDto = new CustomerDto();
    @Autowired
    private SimRepository simRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public SimDto saveSim(SimDto dto, Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.stream().filter(Objects::nonNull)
                .map(sim1 -> {
                    BeanUtils.copyProperties(dto, sim);
                    sim.setCustomer(customer.get());
                    simRepository.save(sim);
                    return sim1;
                });

        return dto;
    }

    @Override
    public List<SimDto> getAllSim() {
        return simRepository.findAll().stream().filter(Objects::nonNull)
                .map(sim1 -> {
                    BeanUtils.copyProperties(sim, dto);
                    return dto;
                }).collect(Collectors.toList());

    }

    @Override
    public List<SimDto> getSimById(Long simId) {

        if (simRepository.findById(simId).isEmpty())
            throw new SimException("Sim not present");
        return simRepository.findById(simId).stream().filter(Objects::nonNull)
                .map(sim1 -> {
                    SimDto dto = new SimDto();
                    BeanUtils.copyProperties(sim1, dto);
                    return dto;
                }).collect(Collectors.toList());

    }

    @Override
    public SimDto updateSimById(SimDto simDto, Long simId) {
        Optional<Sim> simOptional = simRepository.findById(simId);
        if (simOptional.isPresent()) {
            Sim sim = simOptional.get();
            BeanUtils.copyProperties(dto, sim);
            simRepository.save(sim);
        }
        return simDto;
    }
}
