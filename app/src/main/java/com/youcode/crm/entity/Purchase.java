package com.youcode.crm.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private Customer customer;

    private Boolean receiptExist;
    private Boolean invoiceExist;

    @OneToOne(mappedBy = "purchase", cascade = {REMOVE, MERGE})
    private SellingInvoice invoice;

    @NotNull(message = "Purchase date cannot be null")
    private LocalDate purchaseDate;

    @OneToOne(cascade = {REMOVE, MERGE})
    private PurchasePosition purchasePosition;




}
