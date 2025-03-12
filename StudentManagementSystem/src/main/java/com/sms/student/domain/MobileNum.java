package com.sms.student.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Mob_Num")
public class MobileNum {

	@Id
	private String mid;

	@Size(max = 10)
	private String number;

	@NotNull
	private String cardName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	//@JsonBackReference
	//@JsonBackReference
	private Student student;
}
