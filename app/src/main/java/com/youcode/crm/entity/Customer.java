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
@AllArgsConstructor
@Entity @Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Customer firstname cannot be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Customer firstname cannot be blank")
    private String lastName;

    @Column(length = 11)
    private String cin;

    private String zipCode;
    private String city;

    @OneToMany(cascade = {REMOVE, MERGE}, mappedBy = "customer")
    @JsonIgnore
    private Set<Purchase> purchases = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<SellingInvoice> sellingInvoices = new HashSet<>();
}
