package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDTO {
    Long id;
    String name;
    String city;
    //source = "manager.firstName"
//    String managerFirstName;
    //source = "manager.lastName"
//    String managerLastName;
}
