package com.youcode.crm.entity;

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
@Table(name = "PURCHASES_POSITIONS")
public class PurchasePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "PURCHASE_NUMBER")
    private Long id;

    @OneToOne(mappedBy = "purchasePosition", cascade = {REMOVE, MERGE})
    private Purchase purchase;

    @OneToOne(mappedBy = "purchasePosition", cascade = {REMOVE, MERGE})
    private Product product;

    private Double amount;
    private Boolean reclamationExist;


}
