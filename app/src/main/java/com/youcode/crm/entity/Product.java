package com.youcode.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.crm.enums.UNITS_OF_MEASURE;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name ="PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private ProductType type;

    @PositiveOrZero
    private Double sellingPrice;

    @PositiveOrZero
    private Double purchasePrice;

    @Min(0L) @Max(100L)
    private Double taxRate;

    @Enumerated(EnumType.STRING)
    private UNITS_OF_MEASURE unitsOfMeasure;

    @OneToOne(cascade = {REMOVE, MERGE})
    private PurchasePosition purchasePosition;

    @OneToMany(mappedBy = "product", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<ProductUnit> productUnits = new HashSet<>();


}
