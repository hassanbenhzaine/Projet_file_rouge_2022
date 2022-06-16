package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellingInvoiceDTO {
    private Long id;
    private String invoiceDate;
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private Double netWorth;
    private Double grossValue;
    private Double taxRate;
    private String currency;

}
