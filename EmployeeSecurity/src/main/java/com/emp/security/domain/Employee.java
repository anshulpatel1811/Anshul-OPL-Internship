package com.emp.security.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emp_tbl")
public class Employee {

	@Id
	private String empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "emp_password")
	private String empPassword;
	
	@Column(name = "emp_role",length=10)
	private String empRole;
	
	private Boolean isActive;
	
	@OneToOne
	private Image images;
}
