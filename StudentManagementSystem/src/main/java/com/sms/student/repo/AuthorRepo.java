package com.sms.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.student.domain.Author;

public interface AuthorRepo extends JpaRepository<Author,String> {

}
