package com.sim.management.controller;

import com.sim.management.dto.SimDto;
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

    @PostMapping("/saveSim")
    public ResponseEntity<SimDto> SaveSim(@RequestBody SimDto simDto) {
        return new ResponseEntity<>(simService.saveSim(simDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllSim")
    public ResponseEntity<List<SimDto>> getAllSim() {
        List<SimDto> getAllSim = simService.getAllSim();
        return new ResponseEntity<>(getAllSim,HttpStatus.OK);
    }
    @GetMapping("/getSimById/{simId}")
    public ResponseEntity <SimDto> getSimById(@PathVariable Long simId){

        SimDto simDto =  simService.getSimById(simId);

        return new ResponseEntity<>(simDto,HttpStatus.OK);
    }
    @PutMapping("/updateSimById/{simId}")
    public ResponseEntity<SimDto> updateSim(@RequestBody SimDto simDto,@PathVariable Long simId){
        SimDto sim = simService.updateSimById(simDto,simId);
        return new ResponseEntity<>(sim,HttpStatus.CREATED);
    }


}
