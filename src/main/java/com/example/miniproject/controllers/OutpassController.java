package com.example.miniproject.controllers;

import com.example.miniproject.config.auth.TokenProvider;
import com.example.miniproject.dto.StudentDto;
import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.repository.EventRepository;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.repository.StudentRepository;
import com.example.miniproject.services.EventsService;
import com.example.miniproject.services.OutpassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/students")
public class OutpassController {
    Logger logger1 = LoggerFactory.getLogger(OutpassController.class);
    Logger logger2 = LoggerFactory.getLogger(EventsController.class);

    @Autowired
    private OutpassService outpassService;
    @Autowired
    OutpassRepository outpassRepository;
    @Autowired
    StudentRepository studentRepository;

    //giving outpass to the student with rollno
    @CrossOrigin(origins = "*")
    @PostMapping("/{rollNo}/outpass")
    public ResponseEntity<?> addToArchive(@RequestHeader("Authorization") String token,@PathVariable String rollNo) throws UnsupportedEncodingException, JsonProcessingException {
        String payload = token.split("\\.")[1];
        String decodedPayload = new String(Base64.decodeBase64(payload), "UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode payloadNode = objectMapper.readTree(decodedPayload);

        String subject = payloadNode.get("sub").asText();
        String role = payloadNode.get("role").asText();

        if (outpassService.transferStudentToOutpass(rollNo,subject,role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
    //seeing all the students who have outpass
    @CrossOrigin(origins = "*")
    @GetMapping("/outpass/all")
    public List<Outpass> getAllPosts(@RequestHeader("Authorization") String token) {
        LocalDateTime currentTime = LocalDateTime.now();

        // Retrieve students with expiryDate greater than or equal to the current time
        List<Outpass> validStudents = outpassRepository.findByExpiresAtGreaterThanEqual(currentTime);
        return validStudents;
    }

//
//        return outpassRepository.findAll();

    //checking if the student with rollno have outpass or not
    @CrossOrigin(origins = "*")
    @GetMapping("/outpass/{rollNo}")
    public ResponseEntity<?> search(@RequestHeader("Authorization") String token,@PathVariable String rollNo) {
        if (outpassService.doesStudentExistByRollNo(rollNo)) {
            Outpass student = outpassRepository.findByRollNo(rollNo);

            // Check if the student's outpass has expired
            LocalDateTime currentTime = LocalDateTime.now();
            if (student.getExpiresAt().isBefore(currentTime)) {
                // If the outpass has expired, return 403 Forbidden
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Outpass has expired");
            }

            String name = student.getName();
            String rollno = student.getRollNo();
            String branch=student.getBranch();
            int year=student.getYear();
            String phone=student.getPhone();
            return ResponseEntity.ok(new StudentDto(name, rollno,branch,year,phone));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
    //


}