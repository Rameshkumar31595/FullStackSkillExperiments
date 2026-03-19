package com.klu.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klu.model.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        Course savedCourse = service.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = service.getCourseById(id);
        if (course.isPresent()) return ResponseEntity.ok(course.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        try {
            return ResponseEntity.ok(service.updateCourse(id, course));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found for update");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            service.deleteCourse(id);
            return ResponseEntity.ok("Course deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchCourse(@PathVariable String title) {
        List<Course> courses = service.searchByTitle(title);
        if (courses.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found");
        return ResponseEntity.ok(courses);
    }
}