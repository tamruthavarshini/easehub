package com.example.miniproject.model.student;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
public class Student {
    private  String rollNo;
    private String name;
    private String branch;
    private int year;
   private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", year=" + year +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getRollNo() {
        return rollNo;
    }

    public Student() {

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


}
