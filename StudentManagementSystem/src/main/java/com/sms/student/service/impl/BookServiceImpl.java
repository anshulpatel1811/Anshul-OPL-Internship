package com.sms.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sms.student.domain.Author;
import com.sms.student.domain.Book;
import com.sms.student.proxy.BookProxy;
import com.sms.student.repo.BookRepo;
import com.sms.student.service.BookServices;
import com.sms.student.util.ApiResponseMessage;
import com.sms.student.util.ConvertData;

@Service
public class BookServiceImpl implements BookServices{

	@Autowired
	private BookRepo repo;
	
	@Autowired
	private ConvertData c;

	@Override
	public BookProxy create(BookProxy proxy) {
		Book book = c.proxyToDomainForBook(proxy);
		//book.getAuthors().stream().forEach(a->a.setBooks(book.getAuthors().));
		
		repo.save(book);
		return proxy;
	}

	@Override
	public BookProxy updateById(BookProxy proxy, String id) {
		
		Book book = c.proxyToDomainForBook(proxy);
		List<Author> list = book.getAuthors();
//		for (Author br : list) {
//			br.getBooks().stream().forEach(m->m.setAuthors());
//			bookProxy.add(c.domainToProxyForBook(br));
//		}
		
		return null;
	}

	@Override
	public ApiResponseMessage deleteById(String id) {
		Book book = repo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found With this Id !!"));
		repo.delete(book);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Book Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

	@Override
	public BookProxy findById(String id) {
		Book book = repo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found With this Id !!"));
		book.getAuthors().stream().forEach(m->m.setBooks(null));
		BookProxy entityToProxy = c.domainToProxyForBook(book);
		return entityToProxy;
	}

	@Override
	public List<BookProxy> findAll() {
		List<Book> list = repo.findAll();
		
		List<BookProxy> bookProxy = new ArrayList<>();
		
		for (Book br : list) {
			br.getAuthors().stream().forEach(m->m.setBooks(null));
			bookProxy.add(c.domainToProxyForBook(br));
		}
		return bookProxy;
		
	}

	@Override
	public ApiResponseMessage deleteAll() {
		repo.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message(" All Books Deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return message;
	}

}
