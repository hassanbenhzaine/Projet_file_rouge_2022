package com.youcode.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
@Builder
public class AbsenteeismReason {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Absenteeism reason cannot be blank")
    private String name;

    private Boolean consent;
    private String comments;

    @OneToMany(mappedBy = "reasonOfAbsenteeismCode", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Absenteeism> absenteeisms = new HashSet<>();

}
