package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeDTO {

    private Integer id;
    private String fullName;
    private Double discount;
    private Character periodOfAvailability;
}
