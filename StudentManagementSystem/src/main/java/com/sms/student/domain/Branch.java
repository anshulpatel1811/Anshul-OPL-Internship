package com.sms.student.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "branch_tbl")
public class Branch {

	@Id
	@Column(name = "branch_id")
	private String bid;
	
	@Column(name = "branch_name",length = 50)
	private String name;
	
	@Column(name = "branch_des",length = 300)
	private String description;
	
	@OneToOne(mappedBy = "branch",cascade = CascadeType.ALL)
//	@JoinColumn(name = "student_id")
//	@JsonIgnore
	 //@JsonBackReference
	private Student student;
}
