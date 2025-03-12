package com.sms.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.student.domain.MobileNum;

public interface MobileNumRepo extends JpaRepository<MobileNum, String>{

}
