package com.superhero.services;

import java.util.List;

import com.superhero.dtos.SuperHeroDto;

public interface SuperHeroService {

	SuperHeroDto createSuperHero(SuperHeroDto superHeroDto);
	
	List<SuperHeroDto> getAllSuperHeros();
	
	SuperHeroDto findSuperHero(Long id);
	
	SuperHeroDto updateSuperHeroById(SuperHeroDto superHeroDto,Long id);
	
	void deleteSuperHero(Long id);
	
	void deleteAllSuperHeros();
}
