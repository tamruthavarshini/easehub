package com.example.miniproject.repository;

import com.example.miniproject.model.student.Outpass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OutpassRepository extends MongoRepository<Outpass,String> {
    boolean existsByRollNo(String rollNo);
    boolean existsByRollNoAndExpiresAtAfter(String rollNo, LocalDateTime now);
    Outpass findByRollNo(String rollNo);
    List<Outpass> findByExpiresAtGreaterThanEqual(LocalDateTime currentTime);
    List<Outpass> findByCreatedAtGreaterThanEqual(LocalDateTime currentTime);
    List<Outpass> findByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
