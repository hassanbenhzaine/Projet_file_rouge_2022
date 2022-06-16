package com.youcode.wdcmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
public class Deal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private Long value;
    private LocalDate closingDate;

    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "deal") @JsonIgnore
    private List<Proposal> proposalList = new ArrayList<>();
    @ManyToOne
    private Person person;
}
