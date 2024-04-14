package com.example.miniproject.services;

import com.example.miniproject.model.student.Outpass;
import com.example.miniproject.model.student.Student;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.repository.StudentRepository;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

@Service
public class OutpassService {
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
    public boolean transferStudentToOutpass(String rollNo) {
        Student student = studentRepository.findByRollNo(rollNo);
        System.out.println(student);
        if (student != null) {
            if (!doesStudentExistByRollNo(rollNo)) {
                Outpass outpass = new Outpass();
                outpass.setRollNo(student.getRollNo());
                outpass.setName(student.getName());
                outpass.setYear(student.getYear());
                outpass.setBranch(student.getBranch());
                outpassRepository.save(outpass);
            }
            return true;
        }
        else {
            return false;
        }

    }
}
