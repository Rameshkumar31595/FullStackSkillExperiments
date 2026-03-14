package com.klu.studentexception;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")

public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {

        if (id != 101) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }

        return new Student(101, "Mahesh");
    }
}