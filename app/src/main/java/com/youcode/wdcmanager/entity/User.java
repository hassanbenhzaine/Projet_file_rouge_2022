package com.youcode.wdcmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.wdcmanager.entity.enums.Gender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity @ToString @Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Boolean isActive;
    private Gender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDay;
    @ManyToOne
    private Role role;
    private Instant loginExpireAt;

    @OneToMany(mappedBy = "user") @JsonIgnore
    private List<Proposal> proposalList = new ArrayList<>();

    @OneToMany(mappedBy = "owner") @JsonIgnore
    private List<Deal> dealList = new ArrayList<>();

    @OneToMany(mappedBy = "owner") @JsonIgnore
    private List<Organization> organizationList = new ArrayList<>();
}
