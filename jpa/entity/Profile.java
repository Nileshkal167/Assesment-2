package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profile_id;

     private long phone;
     private String address;
     private String city;
     private String state;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "voter_id")

     @JsonBackReference
     private Voter voter;

    public Profile(int i, String s) {

    }
}
