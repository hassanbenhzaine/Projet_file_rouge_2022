package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.User;
import com.youcode.wdcmanager.entity.VerificationToken;
import com.youcode.wdcmanager.repository.UserRepository;
import com.youcode.wdcmanager.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    public VerificationToken create(User user){
        VerificationToken verificationToken = VerificationToken.builder()
                .token(UUID.randomUUID())
                .user(user)
                .expireAt(LocalDateTime.now().plusDays(1))
                .build();
        return verificationTokenRepository.save(verificationToken);
    }

    public void deleteById(Long id){
        verificationTokenRepository.deleteById(id);
    }

    public boolean isTokenValid(UUID token){
        Optional<VerificationToken> tokens = verificationTokenRepository
                .findByTokenAndExpireAtGreaterThan(token, LocalDateTime.now())
                .stream().findFirst();

        tokens.ifPresent(vt -> {
                    User user = vt.getUser();
                    user.setIsActive(true);
                    userRepository.save(user);
        });
        return tokens.isPresent();
    }
}
