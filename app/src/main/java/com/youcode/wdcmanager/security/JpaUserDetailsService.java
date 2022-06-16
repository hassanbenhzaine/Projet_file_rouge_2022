package com.youcode.wdcmanager.security;

import com.youcode.wdcmanager.entity.User;
import com.youcode.wdcmanager.exception.EntityNotFoundException;
import com.youcode.wdcmanager.model.CustomUserDetails;
import com.youcode.wdcmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id)  {
        User user = userRepository.findById(Long.valueOf(id))
            .orElseThrow(() -> {
                throw new EntityNotFoundException("No user found with id ".concat(id));
            });
        return new CustomUserDetails(user);
    }
}
