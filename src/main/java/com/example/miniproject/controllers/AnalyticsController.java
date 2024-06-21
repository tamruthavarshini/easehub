package com.example.miniproject.controllers;
import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.services.AnalyticsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Integer> convertDateToLocalDateTime(@RequestHeader("Authorization") String token,@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateString) {
        Date date = analyticsService.parseDate(dateString);
        LocalDateTime today =analyticsService.convertToLocalDateTime(date);
        LocalDateTime day6 =today.minusDays(1);
        LocalDateTime day5 =today.minusDays(2);
        LocalDateTime day4 =today.minusDays(3);
        LocalDateTime day3 =today.minusDays(4);
        LocalDateTime day2 =today.minusDays(5);
        LocalDateTime day1 =today.minusDays(6);

        LocalDateTime tomorrow=today.plusDays(1);
        List<Outpass> x2= outpassRepository.findByCreatedAtGreaterThanEqual(today);
        List<Outpass> x1= outpassRepository.findByCreatedAtGreaterThanEqual(tomorrow);
        List<Outpass> x3= outpassRepository.findByCreatedAtGreaterThanEqual(day6);
        List<Outpass> x4= outpassRepository.findByCreatedAtGreaterThanEqual(day5);
        List<Outpass> x5= outpassRepository.findByCreatedAtGreaterThanEqual(day4);
        List<Outpass> x6= outpassRepository.findByCreatedAtGreaterThanEqual(day3);
        List<Outpass> x7= outpassRepository.findByCreatedAtGreaterThanEqual(day2);
        List<Outpass> x8= outpassRepository.findByCreatedAtGreaterThanEqual(day1);
        List<Integer> list=new ArrayList<>();
        list.add(x8.size()-x7.size());
        list.add(x7.size()-x6.size());
        list.add(x6.size()-x5.size());
        list.add(x5.size()-x4.size());
        list.add(x4.size()-x3.size());
        list.add(x3.size()-x2.size());
        list.add(x2.size()-x1.size());
        return list;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/data")
    public List<String> dataOfOutpass(@RequestHeader("Authorization") String token,@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String dateString)
    {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return analyticsService.getRollNosByDate(date);

    }





}


