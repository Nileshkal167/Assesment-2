package com.jpa.dto;

import lombok.Data;

@Data
public class ProfileDto {

    private int profile_id;
    private String address;
    private String city;
    private String state;
    private long pin_no;

    public ProfileDto(int i, String s) {

    }

//    @JsonBackReference
//    private VoterDto voterDto;

}
