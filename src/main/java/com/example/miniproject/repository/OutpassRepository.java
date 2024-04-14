package com.example.miniproject.repository;

import com.example.miniproject.model.student.Outpass;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutpassRepository extends MongoRepository<Outpass,String> {
    boolean existsByRollNo(String rollNo);
    Outpass findByRollNo(String rollNo);
}
