package com.blood.bank.service_admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blood.bank.service_admin.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByUsername(String username);

	void deleteByUsername(String username);

}
