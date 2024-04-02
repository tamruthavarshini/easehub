package com.example.miniproject.controllers;

import com.example.miniproject.config.auth.TokenProvider;
import com.example.miniproject.dto.JwtDto;
import com.example.miniproject.dto.LoginDTO;
import com.example.miniproject.user.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenService;

    @CrossOrigin(origins = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid LoginDTO data) {
        logger.info("this is auth controller");
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        User user = (User) authUser.getPrincipal();
        var accessToken = tokenService.generateAccessToken(user);
        String role = user.getRole().name();
        String username = user.getUsername();
        return ResponseEntity.ok(new JwtDto(accessToken, role, username));
    }
}
