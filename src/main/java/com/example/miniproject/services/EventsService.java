package com.example.miniproject.services;

import com.example.miniproject.model.student.Events;
import com.example.miniproject.model.student.Student;
import com.example.miniproject.repository.EventRepository;
import com.example.miniproject.repository.OutpassRepository;
import com.example.miniproject.repository.StudentRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EventsService {
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;
    public boolean doesStudentExistByRollNo(String rollNo) {
        return eventRepository.existsByRollNo(rollNo);
    }
    public List<Student> getListOfStudents(String branch, int year){
        final List<Student> students = new ArrayList<>();
        MongoDatabase database = client.getDatabase("EaseHub");
        MongoCollection<Document> collection = database.getCollection("Students");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                        new Document("equals",
                                new Document("path", "year")
                                        .append("value",year))),
                new Document("$match",
                        new Document("branch", branch)),
                new Document("$sort",
                        new Document("rollNo", 1L))));
        result.forEach(doc -> students.add(converter.read(Student.class,doc)));
        return students;
    }

    public List<Events> getListOfPermittedStudents(String branch, int year){
        final List<Events> events = new ArrayList<>();
        MongoDatabase database = client.getDatabase("EaseHub");
        MongoCollection<Document> collection = database.getCollection("Events");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "event")
                                .append("equals",
                                        new Document("path", "year")
                                                .append("value", 3L))),
                new Document("$match",
                        new Document("branch", "CSE")),
                new Document("$sort",
                        new Document("rollNo", 1L))));
        result.forEach(doc -> events.add(converter.read(Events.class,doc)));
        return events;
    }

    public boolean transferStudentToEvents(List<String> ids) {
        for(int i=0;i<ids.size();i++)
        {
            String s=ids.get(i);
            Student student = studentRepository.findByRollNo(s);

            if (!doesStudentExistByRollNo(s)) {
                Events event = new Events();
                event.setRollNo(student.getRollNo());
                event.setName(student.getName());
                event.setYear(student.getYear());
                event.setBranch(student.getBranch());

                eventRepository.save(event);

            }


        }
        return true;
    }

}
