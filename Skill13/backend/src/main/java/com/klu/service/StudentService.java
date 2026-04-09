package com.klu.service;

import com.klu.entity.Student;
import com.klu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student addStudent(Student s) {
        return repo.save(s);
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student updateStudent(int id, Student s) {
        Student existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(s.getName());
            existing.setEmail(s.getEmail());
            existing.setCourse(s.getCourse());
            return repo.save(existing);
        }
        return null;
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}