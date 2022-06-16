package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.User;
import com.youcode.wdcmanager.entity.VerificationToken;
import com.youcode.wdcmanager.repository.UserRepository;
import com.youcode.wdcmanager.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    public String login(User user){
        Authentication authentification =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authenticated = authenticationManager.authenticate(authentification);
        return jwtUtil.createToken(authenticated.getName());
    }

    public void logout(Long userId){
        userRepository.findById(userId)
                        .ifPresent(user -> {
                            user.setLoginExpireAt(Instant.now());
                            userRepository.save(user);
                    });
    }

    public User register(User user){
        User registeredUser = userService.create(user);
        sendRegisterConfirm(registeredUser);
        return registeredUser;
    }

    public void sendRegisterConfirm(User user){
        VerificationToken verificationToken = verificationTokenService.create(user);
        String token = verificationToken.getToken().toString();

        String emailTemplate = "Hello %s %s,\n\nThank you for registering to our services..\n\nTo complete your registration, please confirm your account by clicking the link below:\n\nhttps://localhost:8081/api/auth/confirm/%s\n\nNotice: link expire after 24 hours from the time this email was sent.\n\nBest regards,\nWDCManager";
        String formattedText = String.format(emailTemplate, user.getFirstName(), user.getLastName(), token);
        emailService.sendSimpleMail(user.getEmail(), formattedText, "Account created confirmation");
    }
}
