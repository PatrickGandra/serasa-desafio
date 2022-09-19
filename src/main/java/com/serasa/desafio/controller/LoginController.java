package com.serasa.desafio.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.desafio.dto.UsuarioDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final SecretKey
            KEY =
            Keys.hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6".getBytes(StandardCharsets.UTF_8));

    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody final UsuarioDTO usuario) {
        try {
            if ("desafio@serasa.com.br".equals(usuario.getNome()) && "1234".equals(usuario.getSenha())) {
                final Date data = new Date();
                final Date dataExpiracao = new Date(System.currentTimeMillis() + 1000 * 30); // 30 seconds
                final String jwtToken = Jwts.builder()
                        .setSubject(usuario.getNome())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(data)
                        .setExpiration(dataExpiracao)
                        .signWith(KEY, SignatureAlgorithm.HS256)
                        .compact();

                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuário e/ou senha inválidos", HttpStatus.UNAUTHORIZED);
            }
        } catch (final Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
