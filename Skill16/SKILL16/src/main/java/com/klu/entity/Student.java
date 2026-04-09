package com.klu.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Student Entity")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Student Name", example = "Shreyash")
    private String name;

    @Schema(description = "Email", example = "abc@gmail.com")
    private String email;

    @Schema(description = "Course", example = "CSE")
    private String course;

    public Student() {}

    public Student(String name, String email, String course) {
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
}