package com.youcode.wdcmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;

    @ManyToOne
    private Organization organization;
    @ManyToOne
    private LeadGroup leadGroup;

    @OneToMany(mappedBy = "person") @JsonIgnore
    private List<Deal> dealList = new ArrayList<>();
}

