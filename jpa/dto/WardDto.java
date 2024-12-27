package com.jpa.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class WardDto {

    private int id;
    private String name;

    @JsonBackReference
    private VoterDto voterDto;
}
