package com.serasa.desafio.filters;

import java.nio.charset.StandardCharsets;

import javax.annotation.Priority;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Authorize
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeFilter implements ContainerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(AuthorizeFilter.class);

    private final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    "7f-j&amp;CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&amp;RNbDHUX6".getBytes(StandardCharsets.UTF_8));

    @Override
    public void filter(final ContainerRequestContext requestContext) {
        try {
            final String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();

            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
        } catch (final Exception exception) {
            log.error("Ocorreu um erro de autorização:: {}", exception.getMessage());
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
