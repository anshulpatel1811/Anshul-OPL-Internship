package com.emp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.entities.EmployeeInfo;


public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
}
