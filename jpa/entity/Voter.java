package com.jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vot_id;

    private String name;

    private int dateOfBirth;

    private long phone;

    @OneToMany(mappedBy = "voter", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Profile> profiles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_id")
    @JsonManagedReference
    private Ward ward;

    public void addProfile(Profile profile) {
        profiles.add(profile);
        profile.setVoter(this);
    }

    public void removeProfile(Profile profile) {
        profiles.remove(profile);
        profile.setVoter(null);
    }

    // Add helper methods for ward
    public void assignWard(Ward ward) {
        this.ward = ward;
        if (ward != null) {
            ward.setVoter(this);
        }
    }
        public void removeWard() {
            if (this.ward != null) {
                this.ward.setVoter(null);
            }
            this.ward = null;
        }
    }
