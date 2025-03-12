package com.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_email",unique = true)
	private String email;
	
	@Column(name = "user_password",length=10)
	private String password;
	
	private String gender;
	
	@Column(length = 1000)
	private String about;
	
	@Column(name = "user_image_name")
	private String imageName;
}
