package com.security.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.learn.domain.Student;
import com.security.learn.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
//	List<Student> student= new ArrayList<>(List.of(
//			new Student(1l, "Anshul", "AHB"),
//			new Student(2l, "Shaym", "AHB"),
//			new Student(3l, "Ram", "AHB")
//			));
//	
//	@GetMapping("/getAllStudent")
//	public List<Student> getAllStudent(){
//		return student;
//	}
//	
//	@GetMapping("/login-form")
//	public String login(){
//		return "Do Login !!";
//	}
//	
//	@GetMapping("/registration")
//	public String registration(){
//		return "Do Registration !!";
//	}
//	
//	@GetMapping("/welcome")
//	public String welcomeMessage(){
//		return "Welcome to Anshul City !!";
//	}
//	
//	@PostMapping("/addStudent")
//	public List<Student> addStudent(@RequestBody Student std){
//		student.add(std);
//		return student;
//	}
	
	// Get all students
    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    // Create a new student
    @PostMapping("/save")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    // Update an existing student
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
