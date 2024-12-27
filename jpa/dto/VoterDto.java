package com.jpa.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class VoterDto {

    private int vot_id;
    private String name;
    private int dateOfBirth;
    private Long phone;

    private List<ProfileDto> profiles;

    private WardDto ward;

}
