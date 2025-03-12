package com.superhero.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superhero.Exception.ListIsEmpty;
import com.superhero.Exception.ResourceNotFoundException;
import com.superhero.dtos.SuperHeroDto;
import com.superhero.entities.SuperHero;
import com.superhero.repositories.SuperHeroRepo;
import com.superhero.services.SuperHeroService;

@Service
public class SuperHeroServiceImpl implements SuperHeroService{

	@Autowired
	private SuperHeroRepo superHeroRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SuperHeroDto createSuperHero(SuperHeroDto superHeroDto) {
		// random id generate
//		String id = UUID.randomUUID().toString();
//		long id2 = Long.parseLong(id);
//		superHeroDto.setId(id2);
		SuperHeroDto heroDto = mapper.map(superHeroRepo.save(mapper.map(superHeroDto, SuperHero.class)), SuperHeroDto.class);
		return heroDto;
	}

	@Override
	public List<SuperHeroDto> getAllSuperHeros() {
		List<SuperHero> list = superHeroRepo.findAll();
		if(list.isEmpty()) {
			throw new ListIsEmpty("No Data is Present!!");
		}
		List<SuperHeroDto> dtoList = list.stream().map(superHero -> mapper.map(superHero, SuperHeroDto.class)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public SuperHeroDto findSuperHero(Long id) {
		SuperHero superHero = superHeroRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SuperHero is not found with given id !!"));
		return mapper.map(superHero, SuperHeroDto.class);
	}

	@Override
	public SuperHeroDto updateSuperHeroById(SuperHeroDto superHeroDto, Long id) {
		SuperHero superHero = superHeroRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SuperHero is not found with given id !!"));
		//superHero.setId(superHeroDto.getId());
		superHero.setUserName(superHeroDto.getUserName());
		superHero.setName(superHeroDto.getName());
		superHero.setGender(superHeroDto.getGender());
		superHero.setEmailId(superHeroDto.getEmailId());
		superHero.setMovies(superHeroDto.getMovies());
		superHero.setMobileNo(superHeroDto.getMobileNo());
		superHero.setAddress(superHeroDto.getAddress());
		superHero.setPinCode(superHeroDto.getPinCode());
		
		SuperHero superHero2 = superHeroRepo.save(superHero);		
		return mapper.map(superHero2, SuperHeroDto.class);
	}

	@Override
	public void deleteSuperHero(Long id) {
		SuperHero superHero = superHeroRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("SuperHero is not found with given id !!"));
		superHeroRepo.delete(superHero);
	}

	@Override
	public void deleteAllSuperHeros() {
		superHeroRepo.deleteAll();
	}

}
