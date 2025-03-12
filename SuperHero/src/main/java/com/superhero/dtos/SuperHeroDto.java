package com.superhero.dtos;

import com.superhero.validate.NotString;
import com.superhero.validate.ValidLong;
import com.superhero.validate.ValidMobileNo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class SuperHeroDto {

	@ValidLong
	private Long id;
	
	@NotBlank
	@Size(min = 5,message = "UserName is not valid !!")
	private String userName;
	
	@NotBlank
	@NotString
	private String name;
	
	private String gender;
	
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
    message = "Invalid email format")
	private String emailId;
	
	@NotBlank
	@NotString
	private String movies;
	
	@ValidMobileNo
	@ValidLong
	private Long mobileNo;
	
	@NotBlank
	private String address;
	
	@NotBlank(message = "PinCode1 is Invalid !!")
	@Pattern(regexp = "^[1-9][0-9]{5}$",message = "PinCode 2is Invalid !!")
	@Size(min = 6,message = "PinCode 3is Invalid !!")
	private String pinCode;
}
