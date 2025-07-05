package com.myproject.backend.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter{//GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtProvider jwtProvider;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        try {
            String jwt = jwtProvider.getToken(httpServletRequest);
            if (jwt != null && jwtProvider.isValidToken(jwt)) {
                    Authentication authentication = jwtProvider.getAuthentication(jwt);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

            this.resetAuthenticationAfterRequest();
        } catch (ExpiredJwtException  eje) {

            LOGGER.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOGGER.debug("Exception " + eje.getMessage(), eje);
        }
    }

    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
