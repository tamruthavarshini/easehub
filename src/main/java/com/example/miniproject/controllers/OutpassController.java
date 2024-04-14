package com.example.miniproject.controllers;

import com.example.miniproject.dto.StudentDto;
import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.repository.EventRepository;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.repository.StudentRepository;
import com.example.miniproject.services.EventsService;
import com.example.miniproject.services.OutpassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "*")
    @PostMapping("/{rollNo}/outpass")
    public ResponseEntity<?> addToArchive(@RequestHeader("Authorization") String token,@PathVariable String rollNo) {
        if (outpassService.transferStudentToOutpass(rollNo)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/outpass/all")
    public List<Outpass> getAllPosts(@RequestHeader("Authorization") String token) {
        return outpassRepository.findAll();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/outpass/{rollNo}")
    public ResponseEntity<?> search(@RequestHeader("Authorization") String token,@PathVariable String rollNo) {
        if (outpassService.doesStudentExistByRollNo(rollNo)) {
            Outpass student = outpassRepository.findByRollNo(rollNo);
            String name = student.getName();
            String rollno = student.getRollNo();
            String branch=student.getBranch();
            int year=student.getYear();
            return ResponseEntity.ok(new StudentDto(name, rollno,branch,year));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }


}
