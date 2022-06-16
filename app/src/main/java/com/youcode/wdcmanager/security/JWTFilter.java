package com.youcode.wdcmanager.security;

import com.youcode.wdcmanager.model.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer")){
            String jwtToken = authHeader.substring(7);

            if(jwtToken != null && !jwtToken.isBlank()){
                Long userId = jwtUtil.getIdClaim(jwtToken).asLong();
                MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(String.valueOf(userId));

                Instant tokenIssuedAt = jwtUtil.getTokenIssuedAt(jwtToken);
                if(tokenIssuedAt.isAfter(userDetails.getTokenExpireAt())) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails.getId(), userDetails.getPassword(), userDetails.getAuthorities());

                    SecurityContext sc = SecurityContextHolder.getContext();
                    if (sc.getAuthentication() == null) sc.setAuthentication(authentication);
                }

            }
        }

        filterChain.doFilter(request,response);
    }
}
