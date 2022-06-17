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
@Builder
@AllArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "Product type cannot be blank")
    private String fullName;
    private Double discount;
    private Character periodOfAvailability;

    @OneToMany(mappedBy = "type", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

}
