package com.example.miniproject.controllers;
import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.services.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @Autowired
    private OutpassRepository outpassRepository;
    @Autowired
    private AnalyticsService analyticsService;
    @CrossOrigin(origins = "*")
    @GetMapping("/count")
    public int convertDateToLocalDateTime(@RequestHeader("Authorization") String token,@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateString) {
        Date date = analyticsService.parseDate(dateString);
        LocalDateTime today =analyticsService.convertToLocalDateTime(date);
        LocalDateTime tomorrow=today.plusDays(1);
        List<Outpass> x1= outpassRepository.findByCreatedAtGreaterThanEqual(today);
        List<Outpass> x2= outpassRepository.findByCreatedAtGreaterThanEqual(tomorrow);
        return x1.size()-x2.size()  ;
    }



}


