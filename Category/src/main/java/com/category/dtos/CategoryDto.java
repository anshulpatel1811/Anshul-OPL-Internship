package com.category.dtos;

import java.util.Date;

import com.category.validation.NotEmptyDouble;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDto {
	
	@NotBlank(message = "Invalid title !!")
	private String title;
	
	private Double price;
	
	@Size(min = 5,max = 40,message = "Invalid title !!")
	@NotBlank
	private String description;
	
	@NotEmptyDouble
	private double stockQuantity;
	
	private Date createdAt;

}
