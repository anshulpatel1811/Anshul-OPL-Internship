package com.ems.employee.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ems.employee.entity.EmployeeProfileImage;

public interface ProfileImageRepo extends JpaRepository<EmployeeProfileImage, Long>{

	Optional<EmployeeProfileImage> findByFileId(String fileId);
	
	@Query(value = "SELECT * FROM profile_media WHERE employee_id=?",nativeQuery = true)
	List<EmployeeProfileImage> findByprofileImage(Long id);
}
