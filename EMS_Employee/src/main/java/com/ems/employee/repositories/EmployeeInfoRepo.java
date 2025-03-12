package com.ems.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.employee.entity.EmployeeInfo;

public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Long>{

}
