package com.ems.employee.entity;

import java.util.ArrayList;
import java.util.List;

import com.ems.employee.enums.GenderType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String empId;
	private String name;
	private String dob;
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<EmployeeProfileImage> profileImage;
}
