package com.youcode.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
@Table(name = "PRODUCT_TYPE")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String fullName;
    private Double discount;
    private Character periodOfAvailability;

    @OneToMany(mappedBy = "type", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

}
