package com.sms.student.domain;

import java.util.List;

import com.sms.student.util.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "std_tbl")
public class Student {

	@Id
	private String sid;
	
	@Column(name = "std_name")
	private String name;
	
	@Column(name = "std_gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "std_dob")
	private String dob;
	
	@Column(name = "std_address",length = 300)
	private String address;
	
	
	//@OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "branch_id")
	//@JsonManagedReference
	private Branch branch;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
	//@JsonManagedReference
	private List<MobileNum> num;
}
