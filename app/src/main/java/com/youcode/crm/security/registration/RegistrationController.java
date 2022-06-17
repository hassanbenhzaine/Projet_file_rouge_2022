package com.youcode.crm.security.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.youcode.crm.controller.ApiMapping.REGISTRATION_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(REGISTRATION_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String register(@RequestBody final RegistrationRequest request) {
        return registrationService.register(request).toString();
    }


    @GetMapping(path = "/confirm", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public String confirm(@RequestParam final UUID token) {
        return registrationService.confirmToken(token);
    }
}
