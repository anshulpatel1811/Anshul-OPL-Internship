package com.security.learn.services;

import java.util.List;

import com.security.learn.domain.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student getStudentById(Long id);

	Student saveStudent(Student student);

	Student updateStudent(Long id, Student student);

	void deleteStudent(Long id);
}
