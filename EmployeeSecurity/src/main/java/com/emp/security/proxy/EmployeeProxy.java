package com.emp.security.proxy;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProxy {
	
	private String empId;
	
	@NotBlank
	private String empName;
	
	@NotBlank
	private String empPassword;
	
	@NotBlank
	private String empRole;// Devloper, Manager, Admin
	
	private Boolean isActive;
	
	private ImageProxy images;
}
