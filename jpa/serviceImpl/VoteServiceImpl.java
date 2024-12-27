package com.jpa.serviceImpl;

import com.jpa.dto.VoterDto;
import com.jpa.dto.VoterWardDTO;
import com.jpa.entity.Profile;
import com.jpa.entity.Voter;
import com.jpa.entity.Ward;
import com.jpa.exception.ResourceNotFoundException;
import com.jpa.repository.ProfileRepository;
import com.jpa.repository.VoterRepository;
import com.jpa.service.VoterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl implements VoterService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private ProfileRepository wardRepository;

    @Autowired
    ModelMapper modelMapper;
    private Object Voter;

    public VoteServiceImpl(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @Override
    public VoterDto getVoterById(int id) {
        Voter voter = voterRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Voter", "Id", id));
       if (voter.getWard()!=null){
           System.out.println(voter.getWard().getName());
       }
        return modelMapper.map(voter, VoterDto.class);

    }

    @Override
    public List<VoterDto> getVoters() {

        return voterRepository.findAll().stream()
                .map(voter -> modelMapper.map(Voter, VoterDto.class))
                .collect(Collectors.toList());
    }


    public VoterWardDTO convertEntityToDTO(Voter voter){
        VoterWardDTO voterWardDTO = new VoterWardDTO();
        voterWardDTO = modelMapper.map(voter, VoterWardDTO.class);
        return voterWardDTO;
    }


    @Override
    public void deleteVoterById(int id) {
        voterRepository.deleteById(id);
    }



    @Override
    public VoterDto updateVoter(Integer id, VoterDto voterDto) {

        Voter existingVoter = voterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Voter", "Id", id));

        existingVoter.setName(voterDto.getName());
        existingVoter.setDateOfBirth(voterDto.getDateOfBirth());
        existingVoter.setPhone(voterDto.getPhone());

        Ward ward = modelMapper.map(voterDto.getWard(), Ward.class);
        existingVoter.getWard().setName(voterDto.getWard().getName());

        // Update the profiles (set the voter reference in each profile)
       List<Profile> profiles = voterDto.getProfiles().stream().map(profileDTO -> {
           return modelMapper.map(profileDTO,Profile.class);
       }).toList();

        if (voterDto.getProfiles() != null) {
            for (Profile profile : profiles) {
                profile.setVoter(existingVoter);
                existingVoter.addProfile(profile);
            }
        }
        ward.setVoter(existingVoter);

        Voter savedVoter = voterRepository.save(existingVoter);
        return modelMapper.map(savedVoter, VoterDto.class);
    }


    @Override
    public VoterDto addVoter(VoterDto voterDto) {

        // Map DTO to entity
        Voter voter = modelMapper.map(voterDto, Voter.class);

        if (voter.getProfiles() != null) {
            for (Profile profile : voter.getProfiles()) {
                profile.setVoter(voter);
            }
        }

        Voter savedVoter = voterRepository.save(voter);

        return modelMapper.map(savedVoter, VoterDto.class);

    }
}
