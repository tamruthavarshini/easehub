package com.example.miniproject.controllers;

import com.example.miniproject.model.student.Student;
import com.example.miniproject.repository.StudentRepository;
import com.example.miniproject.services.OutpassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/faculty")
public class MenteesController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private OutpassService outpassService;
    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public List<Student> menteesList(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException, JsonProcessingException {
        List<String> list=outpassService.getSubAndRole(token);
        String subject=list.get(0);
        String role=list.get(1);

        List<Student> list1 =studentRepository.findByMentorId(subject);
        return list1;

    }
    }
