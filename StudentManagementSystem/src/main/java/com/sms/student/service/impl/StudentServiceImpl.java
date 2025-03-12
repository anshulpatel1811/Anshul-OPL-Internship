package com.sms.student.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sms.student.domain.Branch;
import com.sms.student.domain.MobileNum;
import com.sms.student.domain.Student;
import com.sms.student.exception.ResourceNotFoundException;
import com.sms.student.proxy.BranchProxy;
import com.sms.student.proxy.MobileNumProxy;
import com.sms.student.proxy.StudentProxy;
import com.sms.student.repo.StudentRepo;
import com.sms.student.service.StudentServices;
import com.sms.student.util.ApiResponseMessage;
import com.sms.student.util.ConvertData;

@Service
public class StudentServiceImpl implements StudentServices{

	@Autowired
	private StudentRepo repo;
	
	@Autowired
	private ConvertData c;
	
	@Override
	public StudentProxy create(StudentProxy proxy) {
//		BranchProxy branch = proxy.getBranch();
//		if(branch!=null) {
//			branch.setStudent(proxy);
//		}
		Student student = c.proxyToDomainForStd(proxy);
		
		student.getNum().stream().forEach(m->m.setStudent(student));
		
		Student savedStudent = repo.save(student);
		return proxy;
	}
	
	

	@Override
	public StudentProxy updateById(StudentProxy proxy, String id) {
		if (id != null && repo.findById(id).isPresent()) {
			Student student = c.proxyToDomainForStd(proxy);
			student.getNum().stream().forEach(obj -> obj.setStudent(student));
 
			repo.save(student);
			
			return proxy;
		} else {
			throw new ResourceNotFoundException("id is not found !!");
		}
		
	}

	@Override
	public ApiResponseMessage deleteById(String id) {
		Student student = repo.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found With this Id !!"));
		repo.delete(student);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Student Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

	@Override
	public StudentProxy findById(String id) {
	Student student = repo.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found With this Id !!"));
	
	student.getBranch().setStudent(null);
	student.getNum().stream().forEach(m->m.setStudent(null));
	StudentProxy entityToProxy = c.domainToProxyForStd(student);
	return entityToProxy;
	//return c.domainToProxyForStd(student);
	}

	@Override
	public List<StudentProxy> findAll() {
		List<Student> list = repo.findAll();
		
		List<StudentProxy> studentProxies = new ArrayList<>();
		
		for (Student br : list) {
			br.getBranch().setStudent(null);
			br.getNum().stream().forEach(m->m.setStudent(null));
			studentProxies.add(c.domainToProxyForStd(br));
		}
		return studentProxies;
		
//		List<StudentProxy> list2 = list.stream().map(obj->c.domainToProxyForStd(obj)).collect(Collectors.toList());
//		return list2;
	}

	@Override	
	public ApiResponseMessage deleteAll() {
		repo.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message(" All Student Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

}
