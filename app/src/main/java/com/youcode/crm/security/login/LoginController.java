package com.youcode.crm.security.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.youcode.crm.controller.ApiMapping.LOGIN_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(LOGIN_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {
    private final LoginService loginService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public String login(@RequestBody final LoginRequest request) {
        return loginService.login(request);
    }

}
