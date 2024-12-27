package com.jpa.controller;

import com.jpa.dto.ApiResponse;
import com.jpa.dto.VoterDto;
import com.jpa.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/voters")
public class VoterController {

    @Autowired
    VoterService voterService;


    @GetMapping()
    public ResponseEntity<List<VoterDto>> getVoters(){
        List<VoterDto> voters = voterService.getVoters();
        return ResponseEntity.ok(voters);
    }


    @PostMapping("/add")
    public ResponseEntity<com.jpa.dto.VoterDto> addVoter(@RequestBody VoterDto voterDto){
        VoterDto savedVoter = voterService.addVoter(voterDto);
        return new ResponseEntity<>(savedVoter, HttpStatus.CREATED);
    }


    @GetMapping("/{voterId}")
    public ResponseEntity<VoterDto> getVoterById(@PathVariable Integer voterId){
        VoterDto savedVoter = voterService.getVoterById(voterId);
        return ResponseEntity.ok(savedVoter);
    }


    @DeleteMapping("/{voterId}")
    public ResponseEntity<ApiResponse> deleteVoterById(@PathVariable Integer voterId){
        voterService.deleteVoterById(voterId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully!",true),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<VoterDto> updateVoter(@PathVariable Integer id, @RequestBody VoterDto voterDto){
        VoterDto updatedVoter = voterService.updateVoter(id, voterDto);
        return ResponseEntity.ok(updatedVoter);
    }
}
