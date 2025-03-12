package com.blood.bank.service_user.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProxy {

	private Long id;
	private String name;
	private String gender;
	private String username;
	private String password;
	private String role;
	private Boolean isActive;
}
