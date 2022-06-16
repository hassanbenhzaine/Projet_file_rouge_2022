package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Collection<VerificationToken> findByTokenAndExpireAtGreaterThan(UUID token, LocalDateTime expireAt);
}
