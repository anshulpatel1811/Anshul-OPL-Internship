package com.emp.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.security.domain.Employee;

public interface EmpRepository extends JpaRepository<Employee, String>{

	Optional<Employee> findByEmpName(String name);
}
