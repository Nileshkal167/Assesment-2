package com.jpa.service;

import com.jpa.dto.VoterDto;

import java.util.List;

public interface VoterService {

    VoterDto getVoterById(int id);
    List<VoterDto> getVoters();
    void deleteVoterById(int id);
    VoterDto addVoter(VoterDto voterDto);
    VoterDto updateVoter(Integer id, VoterDto voterDto);

}
