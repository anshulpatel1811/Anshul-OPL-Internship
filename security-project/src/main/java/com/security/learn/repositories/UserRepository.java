package com.security.learn.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.learn.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByUsername(String username);
}
