package com.example.miniproject.controllers;

import com.example.miniproject.dto.JwtDto;
import com.example.miniproject.dto.LoginDTO;
import com.example.miniproject.dto.verifyDto;
import com.example.miniproject.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/verify")
public class VerificationController {
    @CrossOrigin(origins = "*")
    @GetMapping("/token")
    public ResponseEntity<verifyDto> verify(Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new verifyDto(userDetails.getUsername(), userDetails.getRole().name()));
    }

    }
