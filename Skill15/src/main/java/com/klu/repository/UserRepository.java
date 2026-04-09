package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.entity.User;   // ✅ CORRECT
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}