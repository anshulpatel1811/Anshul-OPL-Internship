package com.category.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.category.entities.Category;

public interface CategoryRepositories extends JpaRepository<Category, Long>{

	//List<Category> findByNameContaining(String keyword);
}
