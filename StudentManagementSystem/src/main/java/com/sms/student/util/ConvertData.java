package com.sms.student.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.student.domain.Author;
import com.sms.student.domain.Book;
import com.sms.student.domain.Branch;
import com.sms.student.domain.MobileNum;
import com.sms.student.domain.Student;
import com.sms.student.proxy.AuthorProxy;
import com.sms.student.proxy.BookProxy;
import com.sms.student.proxy.BranchProxy;
import com.sms.student.proxy.MobileNumProxy;
import com.sms.student.proxy.StudentProxy;

@Component
public class ConvertData {

	@Autowired
	private ObjectMapper mapper;

	public Student proxyToDomainForStd(StudentProxy proxy) {
		
		return mapper.convertValue(proxy, Student.class);
	}
	
	public StudentProxy domainToProxyForStd(Student proxy) {
		
		return mapper.convertValue(proxy, StudentProxy.class);
	}
	
	public Branch proxyToDomainForBranch(BranchProxy proxy) {
		
		return mapper.convertValue(proxy, Branch.class);
	}
	
	public BranchProxy domainToProxyForBranch(Branch proxy) {
		
//		BranchProxy brproxy = new BranchProxy();
//        brproxy.setBid(proxy.getBid());
//        brproxy.setName(proxy.getName());
//        brproxy.setDescription(proxy.getDescription());
//        
//        StudentProxy stdProxy=new StudentProxy();
//        stdProxy.setSid(proxy.getStudent().getSid());
//        stdProxy.setName(proxy.getStudent().getName());
//        stdProxy.setAddress(proxy.getStudent().getAddress());
//        stdProxy.setGender(proxy.getStudent().getGender());
//        stdProxy.setBranch(brproxy);
//        
//        brproxy.setStudent(stdProxy);
//
//        return brproxy;
		
		return mapper.convertValue(proxy, BranchProxy.class);
	}
	
	public List<MobileNum> listproxyToDomainForMobile(List<MobileNumProxy> proxy) {
		
		return proxy.stream().map(obj->mapper.convertValue(obj, MobileNum.class)).collect(Collectors.toList());
	}
	
	
	public MobileNum proxyToDomainForMob(MobileNumProxy proxy) {
		
		return mapper.convertValue(proxy, MobileNum.class);
	}
	
	public MobileNumProxy domainToProxyForMob(MobileNum proxy) {
		
		return mapper.convertValue(proxy, MobileNumProxy.class);
	}
	
	public Author proxyToDomainForAuth(AuthorProxy proxy) {
		
		return mapper.convertValue(proxy, Author.class);
	}
	
	public AuthorProxy domainToProxyForAuth(Author proxy) {
		
		return mapper.convertValue(proxy, AuthorProxy.class);
	}
	
	public Book proxyToDomainForBook(BookProxy proxy) {
		
		return mapper.convertValue(proxy, Book.class);
	}
	
	public BookProxy domainToProxyForBook(Book proxy) {
		
		return mapper.convertValue(proxy, BookProxy.class);
	}
	
}
