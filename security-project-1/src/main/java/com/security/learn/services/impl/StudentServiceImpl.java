package com.security.learn.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.learn.domain.Role;
import com.security.learn.domain.Student;
import com.security.learn.repositories.RoleRepository;
import com.security.learn.repositories.StudentRepository;
import com.security.learn.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService,UserDetailsService{

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository repository1;


//	Role role1 = repository1.findByName("Role_Teacher").orElse(null);
//	Role role2 = repository1.findByName("Role_Student").orElse(null);
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		
//		if(role1==null) {
//			
//			role1 = new Role();
//			role1.setName("Role_Teacher");
//			
//			repository1.save(role1);
//		}
//		
//		if(role2==null) {
//			
//			role2 = new Role();
//			role2.setName("Role_Student");
//			
//			repository1.save(role2);
//		}
//		

//	}
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> list = repository.findAll();
		list.stream().forEach(obj->obj.getRoles().stream().forEach(obj2->obj2.setStudents(null)));
		return list;
	}

	@Override
	public Student getStudentById(Long id) {
		Student student = repository.findById(id).orElseThrow(()->new RuntimeException("Student not found with give id !!"));
		List<Role> roles = student.getRoles();
		
		roles.stream().forEach(obj->obj.setStudents(null));


		return student;
	}

	@Override
	public Student saveStudent(Student student) {
		student.setPassword(passwordEncoder.encode(student.getPassword()));
//		List<Role> roles = new ArrayList<Role>();
//		List<Role> list = student.getRoles();
//		System.out.println(list);
//		Object role1="Role_Teacher";
//		Object role2="Role_Student";
//		for (Role role : list) {
//			
//			if(role.equals(role1)) {
//				roles.add(role);
//			}
//			
//			if(role.equals(role2)) {
//				roles.add(role);
//			}
//		}
//		System.out.println(roles);
		//student.setRoles(List.of(role1,role2));
		Student save = repository.save(student);
		return save;
	}

	@Override
	public Student updateStudent(Long id, Student student) {
		Student student1 = repository.findById(id).orElseThrow(()->new RuntimeException("Student not found with give id !!"));
		student1.setName(student.getName());
		//student1.setAddress(student.getAddress());
		//student1.setPassword(student.getPassword());
		//student1.setPassword(passwordEncoder.encode(student.getPassword()));
		//student1.setRole(student.getRole());
		
		repository.save(student1);
		Student student2 = repository.findById(id).orElseThrow(()->new RuntimeException("Student not found with give id !!"));
		List<Role> roles = student2.getRoles();
		
		roles.stream().forEach(obj->obj.setStudents(null));
		return student2;
		
		
	}

	@Override
	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = repository.findByName(username).orElseThrow(()->new RuntimeException("Student not found with give name !!"));
		return student;
	}

}
