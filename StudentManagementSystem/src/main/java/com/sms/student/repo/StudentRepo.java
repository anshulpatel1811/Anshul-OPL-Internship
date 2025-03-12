package com.sms.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.student.domain.Student;

public interface StudentRepo extends JpaRepository<Student, String>{

}
