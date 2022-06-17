package com.youcode.crm.security.registration;

import com.youcode.crm.entity.Employee;
import com.youcode.crm.enums.USER_ROLE;
import com.youcode.crm.security.email.EmailBuilder;
import com.youcode.crm.security.email.EmailSender;
import com.youcode.crm.security.registration.token.ConfirmationToken;
import com.youcode.crm.security.registration.token.ConfirmationTokenService;
import com.youcode.crm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.youcode.crm.controller.ApiMapping.REGISTRATION_REST_URL;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final EmployeeService employeeService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public UUID register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException(
                    String.format("Email %s is not valid!", request.getEmail()));
        }

        UUID token = employeeService.signUpUser(
                Employee.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .userRole(USER_ROLE.EMPLOYEE)
                        .cin(request.getCin())
                        .gender(request.getSex())
                        .birthdate(request.getBirthdate())
                        .salary(request.getSalary())
                        .department(request.getDepartment())
                        .build()
                );

        String link = "http://localhost:8080" + REGISTRATION_REST_URL + "/confirm?token=" + token;
        emailSender.send(request.getEmail(),
                EmailBuilder.buildEmail(request.getFirstName(),
                        link));
        return token;
    }

    @Transactional
    public String confirmToken(UUID token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        employeeService.enableUser(
                confirmationToken.getEmployee().getEmail());
        return "confirmed";
    }
}
