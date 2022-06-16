//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.auth.PersonInfoDto;
//import com.youcode.wdcmanager.dto.auth.RegisterPersonDto;
//import com.youcode.wdcmanager.dto.person.GetPersonDto;
//import com.youcode.wdcmanager.service.AuthService;
//import com.youcode.wdcmanager.service.UserService;
//import com.youcode.wdcmanager.service.VerificationTokenService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.UUID;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final UserService userService;
//    private final AuthService authService;
//    private final ModelMapper modelMapper;
//    private final VerificationTokenService verificationTokenService;
//
//    @PostMapping(value = "/register", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public GetPersonDto register(@RequestBody RegisterPersonDto person) {
//        Person mappedPerson = modelMapper.map(person, Person.class);
//        Person registerdPerson = authService.register(mappedPerson);
//        return modelMapper.map(registerdPerson, GetPersonDto.class);
//    }
//
//    @PostMapping(value = "/login", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> login(@RequestBody Person person) {
//        return ResponseEntity.ok(authService.login(person));
//    }
//
//    @PostMapping(value = "/logout", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> logout(Principal principal){
//        if(principal == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        authService.logout(Long.valueOf(principal.getName()));
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(value = "/userinfo", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<PersonInfoDto> userInfo(Principal principal){
//        if(principal == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        Person user = userService.findById(principal.getName());
//        PersonInfoDto personInfo = modelMapper.map(user, PersonInfoDto.class);
//        return ResponseEntity.ok(personInfo);
//    }
//
//    @GetMapping("/confirm/{token}")
//    public ResponseEntity<Object> confirmAccount(@PathVariable UUID token){
//        if(verificationTokenService.isTokenValid(token)) return ResponseEntity.ok().build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
////    @PostMapping(value = "/resendconfirm/{id}", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
////    public void resendconfirm(@RequestParam Long id){
////        authService.sendRegisterConfirm(Person.builder().id(id).build());
////    }
//
//}
