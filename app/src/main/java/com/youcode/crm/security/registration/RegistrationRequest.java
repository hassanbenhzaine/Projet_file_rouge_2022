package com.youcode.crm.security.registration;

import com.youcode.crm.entity.Department;
import lombok.*;

import java.time.LocalDate;

/**
 * the class contains the fields that are required in the registration form
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cin;
    private String sex;
    private LocalDate birthdate;
    private Double salary;
    private Department department;
}
