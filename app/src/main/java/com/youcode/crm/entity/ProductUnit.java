package com.youcode.crm.entity;

import com.youcode.crm.enums.UNIT_OF_MEASURE;
import lombok.*;

import javax.persistence.*;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class ProductUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private Product product;

    @Enumerated(EnumType.STRING)
    private UNIT_OF_MEASURE unitOfMeasure;

    private String alternativeUnitOfMeasure;
    private Double conversionFactor;

}
