package com.sms.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.student.domain.Branch;

public interface BranchRepo extends JpaRepository<Branch,String> {

}
