package com.example.miniproject.repository;

import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {
    Student findByRollNo(String rollNo);
    List<Student> findByMentorId(String mentorId);
}
