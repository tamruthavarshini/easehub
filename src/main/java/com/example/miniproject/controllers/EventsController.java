package com.example.miniproject.controllers;

import com.example.miniproject.model.requests.RequestObject;
import com.example.miniproject.model.student.Events;
import com.example.miniproject.model.student.Student;
import com.example.miniproject.repository.EventRepository;
import com.example.miniproject.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/students")
public class EventsController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    EventRepository eventRepository;
    @CrossOrigin(origins = "*")
    @GetMapping("/event/permission/{year}/{branch}")
    public List<Student> grantpermission(@RequestHeader("Authorization") String token,@PathVariable String branch, @PathVariable int year)
    {
        return eventsService.getListOfStudents(branch, year);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("event/permissions")
    public ResponseEntity<?> addToEvents(@RequestHeader("Authorization") String token, @RequestBody RequestObject requestObject) {
        if (eventsService.transferStudentToEvents(requestObject)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping("event/{year}/{branch}/permission")
    public List<Events> grantpermissions(@RequestHeader("Authorization") String token, @PathVariable String branch, @PathVariable int year)
    {
        return eventsService.getListOfPermittedStudents(branch, year);
    }



}