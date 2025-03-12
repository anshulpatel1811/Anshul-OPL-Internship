package com.superhero.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.superhero.entities.SuperHero;

public interface SuperHeroRepo extends JpaRepository<SuperHero, Long>{

}
