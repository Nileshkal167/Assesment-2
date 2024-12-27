package com.jpa.dto;

import lombok.Data;

@Data
public class VoterWardDTO {

    private int vot_id;
    private String name;
    private int dateOfBirth;
    private long phone;
    private String address;
    private String city;
    private String state;
    private long pin_no;
}
