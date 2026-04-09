package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.entity.Student;
import com.klu.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "CRUD operations for students")
public class StudentController {

    @Autowired
    private StudentService service;

    @Operation(summary = "Add new student")
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return service.save(s);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getById(id);
    }

    @Operation(summary = "Update student")
    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student s) {
        return service.update(id, s);
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Deleted Successfully";
    }
}