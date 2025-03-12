package com.ems.employee.proxy;

import java.util.List;

import com.ems.employee.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeInfoProxy {

	private Long id;
	private String empId;
	private String name;
	private String dob;
	private GenderType gender;
	private String address;
	
	private List<EmployeeProfileImageProxy> profileImage;
}
