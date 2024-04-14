package com.example.miniproject.repository;

import com.example.miniproject.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,String> {
    Student findByRollNo(String rollNo);
}
