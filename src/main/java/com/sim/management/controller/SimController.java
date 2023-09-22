package com.sim.management.controller;

import com.sim.management.dto.CustomerDto;
import com.sim.management.dto.SimDto;
import com.sim.management.entity.Customer;
import com.sim.management.service.SimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sim")
public class SimController {
    @Autowired
    private SimService simService;

    @PostMapping("/saveSim/{customerId}")
    public ResponseEntity<SimDto> SaveSim(@RequestBody SimDto simDto, @PathVariable Long customerId) {
        return ResponseEntity.ok(simService.saveSim(simDto,customerId));
    }

    @GetMapping("/getAllSim")
    public ResponseEntity<List<SimDto>> getAllSim() {
        return ResponseEntity.ok(simService.getAllSim());
    }
    @GetMapping("/getSimById/{simId}")
    public ResponseEntity <List<SimDto>> getSimById(@PathVariable Long simId){
        return  ResponseEntity.ok(simService.getSimById(simId));
    }
    @PutMapping("/updateSimById/{simId}")
    public ResponseEntity<SimDto> updateSim(@RequestBody SimDto simDto,@PathVariable Long simId){
        return  ResponseEntity.ok(simService.updateSimById(simDto,simId));
    }


}
