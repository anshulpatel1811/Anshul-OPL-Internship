package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.services.Impl.CarImpl;

@RestController
@RequestMapping("car")
public class CarController {
	
	@Autowired
	private CarImpl carImpl;
	
	//get all
	@GetMapping("/getall")
	public List<Car> getAll(Car car) {
		return carImpl.getAll(car);
	}
	
	//get all by id
	
	@GetMapping("/{id}")
	public Car getById(@PathVariable int id) {
		return carImpl.getById(id);
	}
	
	// update
	@GetMapping("/{id}/{carName}")
	public List<Car> carUpdate(@PathVariable Integer id,@PathVariable String carName) {
		return carImpl.carUpdate(id, carName);
	}
	
	// delete
	
	@GetMapping("/delete/{id}")
	public List<Car> deleteById(@PathVariable Integer id) {
		return carImpl.deleteById(id);
	}
}
