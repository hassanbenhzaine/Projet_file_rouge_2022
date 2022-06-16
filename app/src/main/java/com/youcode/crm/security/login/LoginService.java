package com.youcode.crm.security.login;

import com.youcode.crm.service.EmployeeService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final EmployeeService employeeService;
    private final SecretKey secretKey;

    public String login(LoginRequest request) {
        UserDetails userDetails = employeeService.loadUserByUsername(request.getUsername());

        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = Jwts.builder()
                .setSubject(request.getUsername())
                .claim("authorities", authorities)
                .signWith(secretKey)
                .compact();

        return token;
    }


}
