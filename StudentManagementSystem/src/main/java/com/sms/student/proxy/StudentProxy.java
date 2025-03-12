package com.sms.student.proxy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sms.student.util.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class StudentProxy {

	@NotBlank
	private String sid;
	
	@NotBlank
	@Size(min = 4,max = 15)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@NotBlank
	private String dob;
	
	@NotBlank
	@Size(min = 5 ,max = 300)
	private String address;
	
	@NotNull
	private BranchProxy branch;
	
	private List<MobileNumProxy> num;
}
