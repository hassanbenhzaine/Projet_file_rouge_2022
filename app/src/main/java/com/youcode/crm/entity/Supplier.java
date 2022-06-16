package com.youcode.crm.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Supplier name cannot be blank")
    private String name;

    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = {MERGE, REMOVE})
    private TransportType modeOfTransportCode;

    private String activityStatus;

}
