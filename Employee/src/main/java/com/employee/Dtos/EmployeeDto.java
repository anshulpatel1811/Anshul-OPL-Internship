package com.employee.Dtos;

import com.employee.validate.NotEmptyDouble;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeeDto {

	@NotBlank
	private String name;
	@NotBlank
	private String department;
	@NotEmptyDouble
	private double salary;
}
