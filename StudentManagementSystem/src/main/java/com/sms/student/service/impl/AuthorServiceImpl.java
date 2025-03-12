package com.sms.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sms.student.domain.Author;
import com.sms.student.proxy.AuthorProxy;
import com.sms.student.repo.AuthorRepo;
import com.sms.student.service.AuthorServices;
import com.sms.student.util.ApiResponseMessage;
import com.sms.student.util.ConvertData;

@Service
public class AuthorServiceImpl implements AuthorServices{

	@Autowired
	private AuthorRepo repo;
	
	@Autowired
	private ConvertData c;

	@Override
	public AuthorProxy create(AuthorProxy proxy) {
		Author author = c.proxyToDomainForAuth(proxy);
		//book.getAuthors().stream().forEach(a->a.setBooks(book.getAuthors().));
		
		repo.save(author);
		return proxy;
	}

	@Override
	public AuthorProxy updateById(AuthorProxy proxy, String id) {
		
		return null;
	}

	@Override
	public ApiResponseMessage deleteById(String id) {
		Author book = repo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found With this Id !!"));
		repo.delete(book);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Book Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

	@Override
	public AuthorProxy findById(String id) {
		Author book = repo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found With this Id !!"));
		book.getBooks().stream().forEach(m->m.setAuthors(null));
		AuthorProxy entityToProxy = c.domainToProxyForAuth(book);
		return entityToProxy;
	}

	@Override
	public List<AuthorProxy> findAll() {
		List<Author> list = repo.findAll();
		
		List<AuthorProxy> authorProxy = new ArrayList<>();
		
		for (Author ar : list) {
			ar.getBooks().stream().forEach(m->m.setAuthors(null));
			authorProxy.add(c.domainToProxyForAuth(ar));
		}
		return authorProxy;
		
	}

	@Override
	public ApiResponseMessage deleteAll() {
		repo.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message(" All Books Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

}
