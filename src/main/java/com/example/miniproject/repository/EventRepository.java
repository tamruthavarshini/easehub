package com.example.miniproject.repository;

import com.example.miniproject.model.student.Events;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Events,String> {
    boolean existsByRollNo(String rollNo);
    Events findByRollNo(String rollNo);
}
