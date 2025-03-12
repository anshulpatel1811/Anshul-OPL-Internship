package com.security.learn.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.learn.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByName(String name);
}
