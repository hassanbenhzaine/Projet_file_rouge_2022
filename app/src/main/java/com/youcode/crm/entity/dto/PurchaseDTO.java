package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {

    private Long id;
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String purchaseDate;
    private String invoiceId;
}