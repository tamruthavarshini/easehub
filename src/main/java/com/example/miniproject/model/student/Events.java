package com.example.miniproject.model.student;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Events")
public class Events {
    private  String rollNo;
    private String name;
    private String branch;
    private int year;

    @Override
    public String toString() {
        return "NewStudent{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", year=" + year +
                '}';
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Events(String rollNo, String name, String branch, int year) {
        this.rollNo = rollNo;
        this.name = name;
        this.branch = branch;
        this.year = year;
    }
    public Events() {

    }
}
