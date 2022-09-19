package com.serasa.desafio.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.serasa.desafio.service.UserService;
import com.serasa.desafio.utility.JWTUtility;
import io.jsonwebtoken.Claims;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTUtility jwtUtility;

    private final UserService userService;

    public JwtFilter(final JWTUtility jwtUtility, final UserService userService) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");
        Claims token = null;

        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {
            token = jwtUtility.parseToken(authorization.substring(7));
        }

        if (Objects.nonNull(token) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            final UserDetails userDetails = userService.loadUserByUsername(token.getSubject());

            if (Objects.nonNull(userDetails) && jwtUtility.isTokenValid(token)) {
                final UsernamePasswordAuthenticationToken
                        usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
