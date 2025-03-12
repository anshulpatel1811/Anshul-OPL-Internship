package com.sms.student.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class BranchProxy {

	@NotBlank
	private String bid;
	
	@NotBlank
	@Size(max = 30)
	private String name;
	
	@NotBlank
	@Size(max = 300)
	private String description;
	
	//@com.fasterxml.jackson.annotation.JsonBackReference
	private StudentProxy student;
}
