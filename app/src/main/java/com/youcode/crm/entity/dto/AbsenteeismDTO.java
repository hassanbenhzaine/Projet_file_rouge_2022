package com.youcode.crm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbsenteeismDTO {

    private String id;

    private String employeeId;

    //source = "employee.firstName"
    private String employeeFirstName;
    //source = "employee.lastName"
    private String employeeLastName;
    //source = "employee.department.name"
    private String employeeDepartmentName;

    //source = "reasonOfAbsenteeismCode.name"
    private String absenteeismReasonName;
    private Integer absenteeismReasonId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
