package com.youcode.crm.security.login;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
