package com.youcode.crm.entity.dto;

import com.youcode.crm.enums.MODE_OF_TRANSPORT_CODE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    private Long id;
    private String name;
    private String transportTypeFullName;
    private MODE_OF_TRANSPORT_CODE transportTypeId;
    private String activityStatus;

}
