package com.emp.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.security.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

	Optional<Image> findByFileId(String name);

}
