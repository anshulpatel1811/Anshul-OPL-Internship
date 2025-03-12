package com.superhero.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SuperHero {

	@Id
	private Long id;
	private String userName;
	private String name;
	private String gender;
	private String emailId;
	private String movies;
	private Long mobileNo;
	private String address;
	private String pinCode;
}
