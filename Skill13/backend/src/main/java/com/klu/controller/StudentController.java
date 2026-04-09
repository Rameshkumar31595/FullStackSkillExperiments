package com.klu.controller;

import com.klu.entity.Student;
import com.klu.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")   // IMPORTANT for React
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student s) {
        return new ResponseEntity<>(service.addStudent(s), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Student s) {
        Student updated = service.updateStudent(id, s);
        if (updated != null)
            return new ResponseEntity<>(updated, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.deleteStudent(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}