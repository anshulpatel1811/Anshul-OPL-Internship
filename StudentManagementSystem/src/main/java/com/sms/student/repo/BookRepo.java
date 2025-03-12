package com.sms.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.student.domain.Book;

public interface BookRepo extends JpaRepository<Book,String> {

}
