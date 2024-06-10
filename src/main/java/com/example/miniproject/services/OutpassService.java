package com.example.miniproject.services;

import com.example.miniproject.config.auth.TokenProvider;
import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.model.student.Student;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
public class OutpassService {
    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private OutpassRepository outpassRepository;

    public boolean doesStudentExistByRollNo(String rollNo) {
        return outpassRepository.existsByRollNo(rollNo);
    }
    public boolean transferStudentToOutpass(String rollNo,String sub,String role) {
        Student student = studentRepository.findByRollNo(rollNo);
        System.out.println(student.getMentorId());

        if (student != null) {
            if(role.equals("HOD") || student.getMentorId().equals(sub)) {
                if (!doesStudentExistByRollNo(rollNo)) {
                    Outpass outpass = new Outpass();
                    outpass.setRollNo(student.getRollNo());
                    outpass.setName(student.getName());
                    outpass.setYear(student.getYear());
                    outpass.setBranch(student.getBranch());
                    outpass.setPhone(student.getPhone());
                    outpass.setCreatedAt(LocalDateTime.now());
                    LocalDateTime expiryDate = outpass.getCreatedAt().plusHours(6);
                    outpass.setExpiresAt(expiryDate);
                    outpassRepository.save(outpass);
                }
                return true;
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }

    }

}