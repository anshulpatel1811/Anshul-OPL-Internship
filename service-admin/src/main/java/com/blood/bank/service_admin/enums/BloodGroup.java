package com.blood.bank.service_admin.enums;

public enum BloodGroup {

	A_POSITIVE("A+"),
	A_NEGATIVE("A-"),
	B_POSITIVE("B+"),
	B_NEGATIVE("B-"),
	AB_POSITIVE("AB+"),
	AB_NEGATIVE("AB-"),
	O_POSITIVE("O+"),
	O_NEGATIVE("O-");

	private String group;

	private BloodGroup(String group) {
		this.group=group;
	}

	public String getGroup() {
		return group;
	}
}
