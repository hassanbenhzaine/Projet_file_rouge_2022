package com.youcode.crm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Absenteeism {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private Employee employee;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private AbsenteeismReason reasonOfAbsenteeismCode;

    private LocalDate dateFrom;
    private LocalDate dateTo;


}
