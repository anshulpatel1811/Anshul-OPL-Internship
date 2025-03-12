package com.blood.bank.service_admin.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminProxy {

	private Long id;
	private String name;
	private String gender;
	private String username;
	private String password;
	private String role;
	private Boolean isActive;
}
