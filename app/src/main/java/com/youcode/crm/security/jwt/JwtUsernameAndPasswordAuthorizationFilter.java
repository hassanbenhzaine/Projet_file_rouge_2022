package com.youcode.crm.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthorizationFilter extends OncePerRequestFilter {
    private final SecretKey secretKey;
    private final JwtConfiguration jwtConfiguration;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader(jwtConfiguration.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfiguration.getTokenPrefix())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = authorizationHeader.replace(jwtConfiguration.getTokenPrefix(), "");

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            List<String> authorities = (List<String>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthority = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthority
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s can't be trusted", token));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
