package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUnitDTO {

    private Long id;
    private Long productId;
    private String productName;
    private String unitOfMeasure;
    private String alternativeUnitOfMeasure;
    private Double conversionFactor;
}
