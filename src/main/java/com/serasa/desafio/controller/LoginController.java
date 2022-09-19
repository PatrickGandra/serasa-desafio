package com.serasa.desafio.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.model.JwtRequest;
import com.serasa.desafio.model.JwtResponse;
import com.serasa.desafio.service.UserService;
import com.serasa.desafio.utility.JWTUtility;

@RestController
public class LoginController {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public LoginController(final JWTUtility jwtUtility,
                           final AuthenticationManager authenticationManager, final UserService userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody final JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (final BadCredentialsException exception) {
            throw new Exception("Credenciais Inválidas {}", exception);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
