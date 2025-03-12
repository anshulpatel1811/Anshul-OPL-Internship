package com.employee.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Dtos.EmployeeDto;
import com.employee.entities.Employee;
import com.employee.exceptions.ListIsEmptyException;
import com.employee.exceptions.ResourceNotFoundException;
import com.employee.repositories.EmployeeRepository;
import com.employee.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto dto) {
		
		Employee employee = mapper.convertValue(dto, Employee.class);
		Employee savedEmployee = repository.save(employee);
		return mapper.convertValue(savedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto dto, Long id) {
		Employee employee = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with this Id !!"));
		employee.setName(dto.getName());
		employee.setDepartment(dto.getDepartment());
		employee.setSalary(dto.getSalary());
		Employee savedEmployee = repository.save(employee);
		return mapper.convertValue(savedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto findById(Long id) {
		Employee employee = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with this Id !!"));
		return mapper.convertValue(employee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> list = repository.findAll();
		if(list.isEmpty()) {
			throw new ListIsEmptyException("No Data Found !!");
		}
		List<EmployeeDto> DtoList = list.stream().map(obj->mapper.convertValue(obj, EmployeeDto.class)).collect(Collectors.toList());
		return DtoList;
	}

	@Override
	public void deletById(Long id) {
		Employee employee = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with this Id !!"));
		repository.delete(employee);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
		
	}

	@Override
	public List<EmployeeDto> findAllByKeyword(String keyword) {

		List<Employee> list = repository.findByNameContaining(keyword);
		List<EmployeeDto> DtoList = list.stream().map(obj->mapper.convertValue(obj, EmployeeDto.class)).collect(Collectors.toList());
		return DtoList;
	}

}
