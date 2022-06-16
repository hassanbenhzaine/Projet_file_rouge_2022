package com.youcode.wdcmanager.model;

import com.youcode.wdcmanager.entity.Role;
import com.youcode.wdcmanager.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetails implements MyUserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Optional<Role> role = Optional.ofNullable(user.getRole());
        if(role.isEmpty()) throw new RuntimeException("No role found");

        SimpleGrantedAuthority grantedAuthority =
            new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getName()));
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }

    @Override
    public Instant getTokenExpireAt(){
        return user.getLoginExpireAt();
    }

    @Override
    public Long getId() {
        return user.getId();
    }


}
