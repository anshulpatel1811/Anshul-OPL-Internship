package com.harryPotter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harryPotter.proxy.ApiResponce;
import com.harryPotter.proxy.HarryPotterProxy;
import com.harryPotter.services.HarryPotterService;
import com.harryPotter.util.PageableResponse;

@RestController
@RequestMapping("/harry-potter")
public class HarryPotterController {

	@Autowired
	private HarryPotterService service;
	
	@PostMapping
	public ResponseEntity<HarryPotterProxy> create(@RequestBody HarryPotterProxy harPotterProxy) {
		
		HarryPotterProxy proxy = service.create(harPotterProxy);
		return new ResponseEntity<HarryPotterProxy>(proxy, HttpStatus.CREATED);
	}
	
	@PostMapping("/save-bulk-data/{num}")
	public ResponseEntity<String> createAllBulkData(@PathVariable("num") int no) {
		
		service.createBulkData(no);
		return new ResponseEntity<>("Bulk Data Is Saved !!", HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<HarryPotterProxy> getAll() {
		List<HarryPotterProxy> list = service.getAll();
		return list;
	}

	@GetMapping("/{id}")
	public ApiResponce getById(@PathVariable("id") Long id) {
		HarryPotterProxy proxy = service.getById(id);
		ApiResponce responce = ApiResponce.builder().characterName(proxy.getCharacterName()).location(proxy.getLocation()).house(proxy.getHouse()).build();
		return responce;
	}
	
	@GetMapping("/by-sorted")
	public ResponseEntity<Page<HarryPotterProxy>> getAllUsers(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "location",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
	){
		Page<HarryPotterProxy> allData = service.getAllHarrryPotter(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(allData, HttpStatus.OK);
	}

	@GetMapping("/by-sorted-proxy")
	public ResponseEntity<PageableResponse<HarryPotterProxy>> getAllUsers2(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = "characterName",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
	){
		PageableResponse<HarryPotterProxy> response = service.getAllHarrryPotter2(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/house/{house}")
	public ResponseEntity<List<HarryPotterProxy>> findByHouse(@PathVariable("house") String house) {
		
		List<HarryPotterProxy> list = service.findByHouse(house);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{location}/{house}")
	public ResponseEntity<List<HarryPotterProxy>> findByLocationAndHouse(@PathVariable("location") String location, @PathVariable("house") String house) {
		List<HarryPotterProxy> list = service.findByLocationAndHouse(location, house);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/keyword/{keywords}")
	public ResponseEntity<List<HarryPotterProxy>> findBySpellContaining(@PathVariable("keywords") String keywords) {
		List<HarryPotterProxy> list = service.findBySpellContaining(keywords);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/or/{location}/{house}")
	public ResponseEntity<List<HarryPotterProxy>> findByLocationOrHouse(@PathVariable("location") String location, @PathVariable("house") String house) {
		List<HarryPotterProxy> list = service.findByLocationOrHouse(location, house);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteByCharacte_name(@PathVariable("name") String name) {
		
		service.deleteByCharacte_name(name);
		
		return new ResponseEntity<>("Character is deleted Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}/{characterName}")
	public ResponseEntity<String> updateData(@PathVariable("id") Long id,@PathVariable("characterName") String characterName) {
		
		service.updateData(id, characterName);
		
		return new ResponseEntity<>("Character is deleted Successfully", HttpStatus.OK);
	}
	
//	@GetMapping("/twofeild/{id}")
//	public HarryPotterProxy getByIdData(@PathVariable("id") Long id) {
//		HarryPotterProxy proxy = service.getData(id);
//		return proxy;
//	}
}
