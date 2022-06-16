package com.youcode.crm.entity;

import com.youcode.crm.enums.CURRENCY;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "SELLING_INVOICE")
public class SellingInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private LocalDate invoiceDate;

    @ManyToOne(cascade = {REMOVE, MERGE})
    private Customer customer;

    @Column(nullable = false)
    private Double netWorth;

    @Column(nullable = false)
    private Double grossValue;

    @Column(nullable = false)
    @Min(0L) @Max(100L)
    private Double taxRate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CURRENCY currency;

    @OneToOne(cascade = {REMOVE, MERGE})
    private Purchase purchase;

}
