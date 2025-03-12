package com.superhero.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superhero.dtos.SuperHeroDto;
import com.superhero.helper.ApiResponseMessage;
import com.superhero.services.SuperHeroService;

@RestController
@RequestMapping("/superhero")
public class SuperHeroController {

	@Autowired
	private SuperHeroService superHeroService;
	
//	@Autowired
//	private ValidateData validation;
	
	@PostMapping
	public ResponseEntity<SuperHeroDto> createSuperHero(@jakarta.validation.Valid @RequestBody SuperHeroDto superHeroDto){
		
//		//System.out.println(superHeroDto.getId());
//		if(validation.validateData(superHeroDto)) {
//
//			return superHeroService.createSuperHero(superHeroDto);
//		}else {
//			//8System.out.println(superHeroDto.getId());
//
//			return "Data Not Added Successfully...";
		//}
		SuperHeroDto superHero = superHeroService.createSuperHero(superHeroDto);
		return new ResponseEntity<SuperHeroDto>(superHero,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SuperHeroDto>> getAllSuperHeros(){
		List<SuperHeroDto> superHeros = superHeroService.getAllSuperHeros();
		return new ResponseEntity<List<SuperHeroDto>>(superHeros, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuperHeroDto> superHeroGetById(@PathVariable Long id){
		SuperHeroDto superHero = superHeroService.findSuperHero(id);
		return new ResponseEntity<SuperHeroDto>(superHero, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuperHeroDto> updateSuperHeroById(@PathVariable Long id,@jakarta.validation.Valid @RequestBody SuperHeroDto superHeroDto){
		
//		if(validation.validateData(superHeroDto)) {
//			
//			return superHeroService.updateSuperHeroById(superHeroDto, id);
//		}else {
//			return "Data Not Updated Successfully...";
//		}	
		return new ResponseEntity<SuperHeroDto>(superHeroService.updateSuperHeroById(superHeroDto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> deleteSuperHeroById(@PathVariable Long id){
		superHeroService.deleteSuperHero(id);
		ApiResponseMessage message = ApiResponseMessage.builder().message("SuperHero deleted Successfully..").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
	}
	
	@DeleteMapping()
	public ResponseEntity<ApiResponseMessage> deleteALLSuperHero(){
		superHeroService.deleteAllSuperHeros();
		ApiResponseMessage message = ApiResponseMessage.builder().message("All SuperHero deleted Successfully..").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<ApiResponseMessage>(message,HttpStatus.OK);
	}
	
	
}
