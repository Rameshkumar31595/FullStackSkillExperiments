package com.klu.service;

import com.klu.model.Course;
import com.klu.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public Course addCourse(Course course) { return repo.save(course); }
    public List<Course> getAllCourses() { return repo.findAll(); }
    public Optional<Course> getCourseById(Long id) { return repo.findById(id); }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = repo.findById(id).orElseThrow();
        course.setTitle(courseDetails.getTitle());
        course.setDuration(courseDetails.getDuration());
        course.setFee(courseDetails.getFee());
        return repo.save(course);
    }

    public void deleteCourse(Long id) { repo.deleteById(id); }
    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }
}