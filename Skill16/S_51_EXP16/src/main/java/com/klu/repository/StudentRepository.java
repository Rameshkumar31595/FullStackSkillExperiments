package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
